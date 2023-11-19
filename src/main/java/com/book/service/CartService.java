package com.book.service;

import com.book.model.CartDTO;

import java.util.List;

public interface CartService {

    // 장바구니 추가
    int addCart(CartDTO cart);

    // 장바구니 정보 리스트
    List<CartDTO> getCartList(String memId);

    // 카트 수량 수정
    int modifyCount(CartDTO cart);

    // 카트 삭제
    int deleteCart(int cartId);

}
