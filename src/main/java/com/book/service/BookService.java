package com.book.service;

import com.book.model.BookVO;
import com.book.model.CateFilterDTO;
import com.book.model.CateVO;
import com.book.model.Criteria;

import java.util.List;

public interface BookService {

    // 상품 검색
    List<BookVO> getGoodsList(Criteria cri);

    // 상품 총 갯수
    int goodsGetTotal(Criteria cri);

    // 국내 카테고리 리스트
    List<CateVO> getCateCode1();

    // 국외 카테고리 리스트
    List<CateVO> getCateCode2();

    // 검색 결과 카테고리 필터 정보
    List<CateFilterDTO> getCateInfoList(Criteria cri);

    // 상품 정보
    BookVO getGoodsInfo(int bookId);

    // 상품 id 이름
    BookVO getBookIdName(int bookId);

}
