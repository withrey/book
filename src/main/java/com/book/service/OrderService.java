package com.book.service;

import com.book.model.OrderCancelDTO;
import com.book.model.OrderDTO;
import com.book.model.OrderPageItemDTO;

import java.util.List;

public interface OrderService {

    // 주문 정보
    List<OrderPageItemDTO> getGoodsInfo(List<OrderPageItemDTO> orders);

    // 주문
    void order(OrderDTO ord);

    // 주문 취소
    void orderCancel(OrderCancelDTO dto);

}
