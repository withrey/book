package com.book.dao;

import com.book.model.*;

import java.util.List;

public interface AdminDao {

    // 책 등록
    int bookEnroll(BookVO book);

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

    // 이미지 등록
    int imageEnroll(AttachImageVO imageVO);

    // 지정 상품 이미지 전체 삭제
    int deleteImageAll(int bookId);

    // 어제자 날짜 이미지 리스트
    List<AttachImageVO> checkFileList();

    // 지정 상품 이미지 정보 얻기
    List<AttachImageVO> getAttachInfo(int bookId);

    // 주문 상품 리스트
    List<OrderDTO> getOrderList(Criteria cri);

    // 주문 총 갯수
    int getOrderTotal(Criteria cri);

}