package com.book.dao;

import com.book.model.BookVO;
import com.book.model.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void getGoodsListTest() {

        Criteria cri = new Criteria();

        System.out.println("cri = " + cri);

        List<BookVO> list = bookDao.getGoodsList(cri);
        System.out.println("list = " + list);

        System.out.println("==========");
        int goodsTotal = bookDao.goodsGetTotal(cri);
        System.out.println("total = " + goodsTotal);

    }

    @Test
    public void getAuthorIdTest() throws Exception {

        Criteria cri = new Criteria();
        cri.setKeyword("레");

        List<BookVO> list = bookDao.getAuthorIdList(cri.getKeyword());

        System.out.println("결과 : " + list.toString());

        for(int i = 0; i < list.size(); i++) {
            System.out.println("개별 결과 : " + list.get(i));
        }

    }


    // 검색 (동적 쿼리 적용) - 작가
    @Test
    public void getGoodsListTest1() {

        Criteria cri = new Criteria();
        String type = "A";
        String keyword = "이말년";
        String cateCode = "";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setAuthorArr(bookDao.getAuthorIdList(keyword));
        cri.setCateCode(cateCode);

        List<BookVO> list = bookDao.getGoodsList(cri);

        System.out.println("cri : " + cri);
        System.out.println("list : " + list);

    }


    // 검색 (동적 쿼리 적용) - 책제목
    @Test
    public void getGoodsListTest2() {

        Criteria cri = new Criteria();
        String type = "T";
        String keyword = "웹";
        String cateCode = "";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setAuthorArr(bookDao.getAuthorIdList(keyword));
        cri.setCateCode(cateCode);

        List<BookVO> list = bookDao.getGoodsList(cri);

        System.out.println("cri : " + cri);
        System.out.println("list : " + list);

    }


    // 검색 (동적 쿼리 적용) - 카테고리
    @Test
    public void getGoodsListTest3() {

        Criteria cri = new Criteria();
        String type = "C";
        String keyword = "";
        String cateCode = "202001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setAuthorArr(bookDao.getAuthorIdList(keyword));
        cri.setCateCode(cateCode);

        List<BookVO> list = bookDao.getGoodsList(cri);

        System.out.println("cri : " + cri);
        System.out.println("list : " + list);

    }

    // 검색 (동적 쿼리 적용) - 카테고리 + 작가
    @Test
    public void getGoodsListTest4() {

        Criteria cri = new Criteria();
        String type = "AC";
        String keyword = "이말년";
        String cateCode = "202001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setAuthorArr(bookDao.getAuthorIdList(keyword));
        cri.setCateCode(cateCode);

        List<BookVO> list = bookDao.getGoodsList(cri);

        System.out.println("cri : " + cri);
        System.out.println("list : " + list);

    }


    // 검색 (동적 쿼리 적용) - 카테고리 + 책 제목
    @Test
    public void getGoodsListTest5() {

        Criteria cri = new Criteria();
        String type = "CT";
        String keyword = "service";
        String cateCode = "202001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setAuthorArr(bookDao.getAuthorIdList(keyword));
        cri.setCateCode(cateCode);

        List<BookVO> list = bookDao.getGoodsList(cri);

        System.out.println("cri : " + cri);
        System.out.println("list : " + list);

    }

    // 카테고리 리스트
    @Test
    public void getCateListTest1() {

        Criteria cri = new Criteria();

        String type = "TC";
        String keyword = "테스트";

        cri.setType(type);
        cri.setKeyword(keyword);

        List cateList = bookDao.getCateList(cri);
        for(Object codeNum : cateList) {
            System.out.println("codeNum = " + codeNum);
        }

    }

    // 카테고리 정보 얻기
    @Test
    public void getCateInfoTest1() {

        Criteria cri = new Criteria();

        String type = "TC";
        String keyword = "테스트";
        String cateCode = "202001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setCateCode(cateCode);

        bookDao.getCateInfo(cri);

    }

    // 상품 정보
    @Test
    public void getGoodsInfoTest() {

        int bookId = 964;
        BookVO goodsInfo = bookDao.getGoodsInfo(bookId);
        System.out.println("===========================");
        System.out.println(goodsInfo);
        System.out.println("===========================");

    }



}