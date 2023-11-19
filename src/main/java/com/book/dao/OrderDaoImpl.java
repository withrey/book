package com.book.dao;

import com.book.model.BookVO;
import com.book.model.OrderDTO;
import com.book.model.OrderItemDTO;
import com.book.model.OrderPageItemDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.OrderMapper.";

    // 주문 상품 정보(주문 페이지)
    @Override
    public OrderPageItemDTO getGoodsInfo(int bookId) {

        return session.selectOne(namespace + "getGoodsInfo", bookId);

    }

    // 주문 상품 정보(주문 처리)
    @Override
    public OrderItemDTO getOrderInfo(int bookId) {

        return session.selectOne(namespace+"getOrderInfo", bookId);

    }

    // 주문 테이블 등록
    @Override
    public int enrollOrder(OrderDTO ord) {

        return session.insert(namespace+"enrollOrder", ord);

    }

    // 주문 아이템 테이블 등록
    @Override
    public int enrollOrderItem(OrderItemDTO orid) {

        return session.insert(namespace+"enrollOrderItem", orid);

    }

    // 주문 재고 차감
    @Override
    public int deductStock(BookVO book) {

        return session.update(namespace+"deductStock", book);

    }

    // 주문 취소
    @Override
    public int orderCancel(String orderId) {

        return session.update(namespace+"orderCancel", orderId);

    }

    // 주문 상품 정보(주문 취소)
    @Override
    public List<OrderItemDTO> getOrderItemInfo(String orderId) {

        return session.selectList(namespace+"getOrderItemInfo", orderId);

    }

    // 주문 정보(주문 취소)
    @Override
    public OrderDTO getOrder(String orderId) {

        return session.selectOne(namespace+"getOrder", orderId);

    }

}
