package com.book.dao;

import com.book.model.CartDTO;

import java.util.List;

public interface CartDao {

    // 카트 추가
    int addCart(CartDTO cart) throws Exception ;

    // 카트 삭제
    int deleteCart(int cartId);

    // 카트 수량 수정
    int modifyCount(CartDTO cart);

    // 카트 목록
    List<CartDTO> getCart(String memId);

    // 카트 확인
    CartDTO checkCart(CartDTO cart);

    // 카트 제거(주문)
    int deleteOrderCart(CartDTO cart);

}
