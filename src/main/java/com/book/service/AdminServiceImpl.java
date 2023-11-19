package com.book.service;

import com.book.dao.AdminDao;
import com.book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    // 상품 등록
    @Transactional
    @Override
    public void bookEnroll(BookVO book) {

        adminDao.bookEnroll(book);

        // 이미지 파일 첨부유무 확인
        if(book.getImageList() == null || book.getImageList().size() <= 0) {
            return;
        }

        // 이미지 파일
        book.getImageList().forEach(attach -> {

            attach.setBookId(book.getBookId());
            adminDao.imageEnroll(attach);

        });

    }

    // 카테고리 리스트
    @Override
    public List<CateVO> cateList() {

        return adminDao.cateList();

    }

    // 상품 리스트
    public List<BookVO> goodsGetList(Criteria cri) {

        return adminDao.goodsGetList(cri);

    }

    // 상품 총 개수
    public int goodsGetTotal(Criteria cri) {

        return adminDao.goodsGetTotal(cri);

    }

    // 상품 조회 페이지
    @Override
    public BookVO goodsGetDetail(int bookId) {

        return adminDao.goodsGetDetail(bookId);

    }

    // 상품 수정
    @Transactional
    @Override
    public int goodsModify(BookVO book) {

        int result = adminDao.goodsModify(book);

        if(result == 1 && book.getImageList() != null && book.getImageList().size() > 0 ) {

            adminDao.deleteImageAll(book.getBookId());

            book.getImageList().forEach(attach -> {

                attach.setBookId(book.getBookId());
                adminDao.imageEnroll(attach);

            });
        }

        return result;

    }

    // 상품 삭제
    @Transactional
    @Override
    public int goodsDelete(int bookId) {

        adminDao.deleteImageAll(bookId);

        return adminDao.goodsDelete(bookId);

    }

    // 지정 상품 이미지 정보 얻기
    @Override
    public List<AttachImageVO> getAttachInfo(int bookId) {

        return adminDao.getAttachInfo(bookId);

    }

    // 주문 상품 리스트
    @Override
    public List<OrderDTO> getOrderList(Criteria cri) {

        return adminDao.getOrderList(cri);

    }

    // 주문 총 갯수
    @Override
    public int getOrderTotal(Criteria cri) {

        return adminDao.getOrderTotal(cri);

    }

}
