package com.book.dao;

import com.book.model.AttachImageVO;
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
public class AdminDaoImplTest {

    @Autowired
    private AdminDao dao;

    // 책 등록
    @Test
    public void bookEnrollTest() throws Exception {

        BookVO book = new BookVO();

        book.setBookName("dao 테스트");
        book.setAuthorId(30);
        book.setBookDate("2021-03-18");
        book.setBookPuble("출판사 테스트");
        book.setCateCode("");
        book.setBookPrice(20000);
        book.setBookStock(10);
        book.setBookDiscount(0.23);
        book.setBookInfo("책 정보 테스트");

        dao.bookEnroll(book);

    }

    // 카테고리 리스트
    @Test
    public void cateListTest() throws Exception {

        System.out.println("cateList = " + dao.cateList());

    }

    // 상품 리스트 & 상품 총 개수
    @Test
    public void goodsGetListTest() {

        Criteria cri = new Criteria();

        // 검색 조건
        cri.setKeyword("테스트");

        // 검색 리스트
        List list = dao.goodsGetList(cri);
        for(int i = 0; i < list.size(); i++) {
            System.out.println("result = " + i + " : " + list.get(i));
        }

        // 상품 총 개수
        int result = dao.goodsGetTotal(cri);
        System.out.println("result = " + result);

    }

    // 상품 조회 페이지
    @Test
    public void goodsGetDetailTest() {

        int bookId = 7;
        
        BookVO result = dao.goodsGetDetail(bookId);

        System.out.println("result = " + result);

    }

    // 상품 정보 수정
    @Test
    public void goodsModifyTest() {

        BookVO book = new BookVO();

        book.setBookId(7);
        book.setBookName("Test 테스트");
        book.setAuthorId(1);
        book.setBookDate("2021-03-18");
        book.setBookPuble("Test 출판사");
        book.setCateCode("103002");
        book.setBookPrice(20000);
        book.setBookStock(300);
        book.setBookDiscount(0.11);
        book.setBookInfo("Test 책정보");

        dao.goodsModify(book);

    }

    // 상품 정보 삭제
    @Test
    public void goodsDeleteTest() {

        int bookId = 7;

        int result = dao.goodsDelete(bookId);

        if(result == 1) {
            System.out.println("삭제 성공");
        }

    }

    // 이미지 등록 테스트
    @Test
    public void imageEnrollTest() {

        AttachImageVO imageVO = new AttachImageVO();

        imageVO.setBookId(9);
        imageVO.setFileName("test");
        imageVO.setUploadPath("test");
        imageVO.setUuid("test");

        dao.imageEnroll(imageVO);

    }

    // 지정 상품 이미지 전체 삭제
    @Test
    public void deleteImageAllTest() {

        int bookId = 11;

        dao.deleteImageAll(bookId);

    }

    // 어제자 날짜 이미지 리스트
    @Test
    public void checkImageListTest() {

        dao.checkFileList();

    }

    // 지정 상품 이미지 정보 얻기
    @Test
    public void getAttachInfoTest() {

        int bookId = 15;
        
        List<AttachImageVO> list = dao.getAttachInfo(bookId);

        System.out.println("list = " + list);

    }

}