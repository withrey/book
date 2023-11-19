package com.book.service;

import com.book.model.*;

import java.util.List;

public interface AdminService {

    // 책 등록
    void bookEnroll(BookVO book);

    // 카테고리 리스트
    List<CateVO> cateList();

    // 상품 리스트
    List<BookVO> goodsGetList(Criteria cri);

    // 상품 총 개수
    int goodsGetTotal(Criteria cri);

    // 상품 조회 페이지
    BookVO goodsGetDetail(int bookId);

    // 상품 수정
    int goodsModify(BookVO book);

    // 상품 삭제
    int goodsDelete(int bookId);

    // 지정 상품 이미지 정보 얻기
    List<AttachImageVO> getAttachInfo(int bookId);

    // 주문 상품 리스트
    List<OrderDTO> getOrderList(Criteria cri);

    // 주문 총 갯수
    int getOrderTotal(Criteria cri);

}
