package com.book.service;

import com.book.dao.AttachDao;
import com.book.dao.CartDao;
import com.book.model.AttachImageVO;
import com.book.model.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDao cartDao;

    @Autowired
    private AttachDao attachDao;


    // 장바구니 추가
    @Override
    public int addCart(CartDTO cart) {

        // 장바구니 데이터 체크
        // 이미 DB에 존재시 2 반환
        CartDTO checkCart = cartDao.checkCart(cart);
        if(checkCart != null) {
            return 2;
        }

        // 장바구니 등록
        // 에러시 0 반환
        try {

            return cartDao.addCart(cart);

        } catch (Exception e) {

            return 0;

        }
    }

    // 장바구니 정보 리스트
    @Override
    public List<CartDTO> getCartList(String memId) {

        List<CartDTO> cart = cartDao.getCart(memId);

        for(CartDTO dto : cart) {

            // 종합 정보 초기화
            dto.initSaleTotal();

            // 이미지 정보 얻기
            int bookId = dto.getBookId();

            List<AttachImageVO> imageList = attachDao.getAttachList(bookId);

            dto.setImageList(imageList);

        }

        return cart;

    }

    // 카트 수량 수정
    @Override
    public int modifyCount(CartDTO cart) {

        return cartDao.modifyCount(cart);

    }

    // 카트 삭제
    @Override
    public int deleteCart(int cartId) {

        return cartDao.deleteCart(cartId);

    }


}
