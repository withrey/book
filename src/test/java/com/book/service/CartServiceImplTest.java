package com.book.service;

import com.book.model.CartDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartServiceImplTest {

    @Autowired
    private CartService cartService;

    // 등록 테스트
    @Test
    public void addCart() {

        String memId = "2222";
        int bookId = 950;
        int count = 1;

        CartDTO dto = new CartDTO();
        dto.setMemId(memId);
        dto.setBookId(bookId);
        dto.setBookCount(count);

        int result = cartService.addCart(dto);

        System.out.println("** result : " + result);

    }
}