package com.book.dao;

import com.book.model.BookVO;
import com.book.model.CartDTO;
import com.book.model.OrderDTO;
import com.book.model.OrderItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class OrderDaoImplTest {

    @Autowired
    private OrderDao orderDao;


    // 상품 정보(주문 처리)
    @Test
    public void getOrderInfoTest() {

        OrderItemDTO orderInfo = orderDao.getOrderInfo(9);

        System.out.println("result : " + orderInfo);

    }


    // order 테이블 등록
    @Test
    public void enrollOrderTest() {

        OrderDTO ord = new OrderDTO();
        List<OrderItemDTO> orders = new ArrayList<>();

        OrderItemDTO order1 = new OrderItemDTO();

        order1.setBookId(9);
        order1.setBookCount(5);
        order1.setBookPrice(10000);
        order1.setBookDiscount(0.0);
        order1.initSaleTotal();


        ord.setOrders(orders);

        ord.setOrderId("test1");
        ord.setAddressee("test1");
        ord.setMemId("2222");
        ord.setMemAddr1("test1Addr1");
        ord.setMemAddr2("test1Addr2");
        ord.setMemAddr3("test1Addr3");
        ord.setOrderState("배송준비");
        ord.getOrderPriceInfo();

        orderDao.enrollOrder(ord);

    }


    // orderitem 테이블 등록
    @Test
    public void enrollOrderItemTest() {

        OrderItemDTO oid = new OrderItemDTO();

        oid.setOrderId("test1");
        oid.setBookId(9);
        oid.setBookCount(1);
        oid.setBookPrice(10000);
        oid.setBookDiscount(0.1);

        oid.initSaleTotal();

        orderDao.enrollOrderItem(oid);

    }

    // 상품 재고 변경
    @Test
    public void deductStockTest() {

        BookVO book = new BookVO();

        book.setBookId(9);
        book.setBookStock(99);

        orderDao.deductStock(book);

    }



}