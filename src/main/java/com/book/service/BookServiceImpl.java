package com.book.service;

import com.book.dao.AdminDao;
import com.book.dao.AttachDao;
import com.book.dao.BookDao;
import com.book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AttachDao attachDao;

    @Autowired
    private AdminDao adminDao;

    // 상품 검색
    @Override
    public List<BookVO> getGoodsList(Criteria cri) {

        String type = cri.getType();
        String[] typeArr = type.split("");
        List<BookVO> authorArr = bookDao.getAuthorIdList(cri.getKeyword());

        // 검색 조건이 작가 일 경우에만 실행
        if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
            if(authorArr.size() == 0) {
                return new ArrayList();
            }
        }

        for(String t : typeArr) {
            if(t.equals("A")) {
                cri.setAuthorArr(authorArr);
            }
        }

        List<BookVO> list = bookDao.getGoodsList(cri);

        list.forEach(bookVO -> {

            int bookId = bookVO.getBookId();

            List<AttachImageVO> imageList = attachDao.getAttachList(bookId);

            bookVO.setImageList(imageList);

        });

//        return bookDao.getGoodsList(cri);
        return list;

    }

    // 상품 총 갯수
    @Override
    public int goodsGetTotal(Criteria cri) {

        return bookDao.goodsGetTotal(cri);

    }

    // 국내 카테고리 리스트
    @Override
    public List<CateVO> getCateCode1() {

        return bookDao.getCateCode1();

    }

    // 국외 카테고리 리스트
    @Override
    public List<CateVO> getCateCode2() {

        return bookDao.getCateCode2();

    }

    // 검색 결과 카테고리 필터 정보
    @Override
    public List<CateFilterDTO> getCateInfoList(Criteria cri) {

        List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();

        String[] typeArr = cri.getType().split("");
        List authorArr;

        for(String type : typeArr) {
            if(type.equals("A")) {
                authorArr = bookDao.getAuthorIdList(cri.getKeyword());

                if(authorArr.size() == 0) {
                    return filterInfoList;
                }
                cri.setAuthorArr(authorArr);
            }
        }

        List cateList = bookDao.getCateList(cri);

        String tempCateCode = cri.getCateCode();

        for(Object cateCode : cateList) {
            cri.setCateCode((String)cateCode);
            CateFilterDTO filterInfo = bookDao.getCateInfo(cri);
            filterInfoList.add(filterInfo);
        }

        cri.setCateCode(tempCateCode);

        return filterInfoList;

    }

    // 상품 정보
    @Override
    public BookVO getGoodsInfo(int bookId) {

        BookVO goodsInfo = bookDao.getGoodsInfo(bookId);
        goodsInfo.setImageList(adminDao.getAttachInfo(bookId));

        return goodsInfo;

    }

}
