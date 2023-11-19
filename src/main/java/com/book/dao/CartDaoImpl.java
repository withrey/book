package com.book.dao;

import com.book.model.CartDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SqlSession session;

    private String namespace = "com.book.mapper.CartMapper.";


    // 카트 추가
    @Override
    public int addCart(CartDTO cart) throws Exception {

        return session.insert(namespace+"addCart", cart);

    }

    // 카트 삭제
    @Override
    public int deleteCart(int cartId) {

        return session.delete(namespace+"deleteCart", cartId);

    }

    // 카트 수량 수정
    @Override
    public int modifyCount(CartDTO cart) {

        return session.update(namespace+"modifyCount", cart);

    }

    // 카트 목록
    @Override
    public List<CartDTO> getCart(String memId) {

        return session.selectList(namespace+"getCart", memId);

    }

    // 카트 확인
    @Override
    public CartDTO checkCart(CartDTO cart) {

        return session.selectOne(namespace+"checkCart", cart);

    }

    // 카트 제거(주문)
    @Override
    public int deleteOrderCart(CartDTO cart) {

        return session.delete(namespace+"deleteOrderCart", cart);

    }


}
