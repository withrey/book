package com.book.service;

import com.book.model.BookVO;
import com.book.model.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService service;

    @Test
    public void getCateInfoList1() {

        Criteria cri = new Criteria();

        String type = "TC";
        String keyword = "테스트";
        String cateCode = "202001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setCateCode(cateCode);

        System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));

    }


    @Test
    public void getCateInfoList2() {

        Criteria cri = new Criteria();

        String type = "AC";
        String keyword = "이말년";
        String cateCode = "102001";

        cri.setType(type);
        cri.setKeyword(keyword);
        cri.setCateCode(cateCode);

        System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));

    }

    // 상품 정보
    @Test
    public void getGoodsInfoTest() {

        int bookId = 963;
        BookVO goodsInfo = service.getGoodsInfo(bookId);
        System.out.println("==========================");
        System.out.println("전체 : " + goodsInfo);
        System.out.println("bookId : " + goodsInfo.getBookId());
        System.out.println("이미지 정보 : " + goodsInfo.getImageList().isEmpty());

    }


}