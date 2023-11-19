package com.book.service;

import com.book.dao.AdminDao;
import com.book.model.AttachImageVO;
import com.book.model.AuthorVO;
import com.book.model.BookVO;
import com.book.model.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceImplTest {

    @Autowired
    private AdminDao dao;

    @Autowired
    private AdminService service;

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

    // 이미지 등록 테스트(상품 등록 시)
    @Test
    public void bookEnrollTest() {

        BookVO book = new BookVO();

        // 책 정보
        book.setBookName("service 테스트");
        book.setAuthorId(3);
        book.setBookDate("2023-10-29");
        book.setBookPuble("출판사");
        book.setCateCode("202001");
        book.setBookPrice(20000);
        book.setBookStock(20);
        book.setBookDiscount(0.11);
        book.setBookInfo("책 소개");

        // 이미지 정보

        List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();

        AttachImageVO image1 = new AttachImageVO();
        AttachImageVO image2 = new AttachImageVO();

        image1.setFileName("test Image 1");
        image1.setUploadPath("test Image 1");
        image1.setUuid("test111");

        image2.setFileName("test Image aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2");
        image2.setUploadPath("test Image 2");
        image2.setUuid("test222");

        imageList.add(image1);
        imageList.add(image2);

        book.setImageList(imageList);

        service.bookEnroll(book);

        System.out.println("등록된 VO : " + book);

    }


}