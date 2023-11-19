package com.book.dao;

import com.book.model.BookVO;
import com.book.model.OrderDTO;
import com.book.model.OrderItemDTO;
import com.book.model.OrderPageItemDTO;

import java.util.List;

public interface OrderDao {

    // 주문 상품 정보(주문 페이지)
    OrderPageItemDTO getGoodsInfo(int bookId);

    // 주문 상품 정보(주문 처리)
    OrderItemDTO getOrderInfo(int bookId);

    // 주문 테이블 등록
    int enrollOrder(OrderDTO ord);

    // 주문 아이템 테이블 등록
    int enrollOrderItem(OrderItemDTO orid);

    // 주문 재고 차감
    int deductStock(BookVO book);

    // 주문 취소
    int orderCancel(String orderId);

    // 주문 상품 정보(주문 취소)
    List<OrderItemDTO> getOrderItemInfo(String orderId);

    // 주문 정보(주문 취소)
    OrderDTO getOrder(String orderId);


}
