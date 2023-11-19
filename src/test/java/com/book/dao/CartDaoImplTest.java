package com.book.dao;

import com.book.model.CartDTO;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CartDaoImplTest {

    @Autowired
    private CartDao cartDao;

//    @Test
//    public void addCartTest() {
//
//        String memId = "2222";
//        int bookId = 961;
//        int count = 1;
//
//        CartDTO cart = new CartDTO();
//        cart.setmemId(memId);
//        cart.setBookId(bookId);
//        cart.setBookCount(count);
//
//        int result = 0;
//        result = cartDao.addCart(cart);
//
//        System.out.println("결과 : " + result);
//
//    }

    @Test
    public void deleteCartTest() {

        int cartId = 1;

        cartDao.deleteCart(cartId);

    }

    @Test
    public void modifyCountTest() {

        int cartId = 1;
        int count = 3;

        CartDTO cart = new CartDTO();
        cart.setCartId(cartId);
        cart.setBookCount(count);

        cartDao.modifyCount(cart);

    }

    @Test
    public void getCartTest() {

        String memId = "2222";

        List<CartDTO> list = cartDao.getCart(memId);
        for(CartDTO cart : list) {
            System.out.println(cart);
            cart.initSaleTotal();
            System.out.println("init cart : " + cart);
        }

    }

    @Test
    public void checkCartTest() {

        String memId = "2222";
        int bookId = 962;

        CartDTO cart = new CartDTO();
        cart.setMemId(memId);
        cart.setBookId(bookId);

        CartDTO resultCart = cartDao.checkCart(cart);
        System.out.println("결과 : " + resultCart);

    }

    // 장바구니 제거(주문 처리)
    @Test
    public void deleteOrderCart() {
        String memId = "2222";
        int bookId = 963;

        CartDTO cart = new CartDTO();
        cart.setMemId(memId);
        cart.setBookId(bookId);

        cartDao.deleteOrderCart(cart);
    }


}