package com.book.dao;

import com.book.model.*;

import java.util.List;

public interface BookDao {

    // 상품 검색
    List<BookVO> getGoodsList(Criteria cri);

    // 상품 총 갯수
    int goodsGetTotal(Criteria cri);

    // 작가 아이디 리스트 요청
    List<BookVO> getAuthorIdList(String keyword);

    // 국내 카테고리 리스트
    List<CateVO> getCateCode1();

    // 국외 카테고리 리스트
    List<CateVO> getCateCode2();

    // 검색 대상 카테고리 리스트
    List getCateList(Criteria cri);

    // 카테고리 정보(+검색대상 갯수)
    CateFilterDTO getCateInfo(Criteria cri);

    // 상품 정보
    BookVO getGoodsInfo(int bookId);

    // 상품 id 이름
    BookVO getBookIdName(int bookId);

    // 평점순 상품 정보
    List<SelectDTO> likeSelect();



}
