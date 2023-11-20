package com.book.dao;

import com.book.model.BookVO;
import com.book.model.CateFilterDTO;
import com.book.model.CateVO;
import com.book.model.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.BookMapper.";


    // 상품 검색
    @Override
    public List<BookVO> getGoodsList(Criteria cri) {

        return session.selectList(namespace + "getGoodsList", cri);

    }

    // 상품 총 갯수
    @Override
    public int goodsGetTotal(Criteria cri) {

        return session.selectOne(namespace + "goodsGetTotal", cri);

    }

    // 작가 아이디 리스트 요청
    @Override
    public List<BookVO> getAuthorIdList(String keyword) {

        return session.selectList(namespace + "getAuthorIdList", keyword);

    }

    // 국내 카테고리 리스트
    @Override
    public List<CateVO> getCateCode1() {

        return session.selectList(namespace+"getCateCode1");

    }

    // 국외 카테고리 리스트
    @Override
    public List<CateVO> getCateCode2() {

        return session.selectList(namespace+"getCateCode2");

    }

    // 검색 대상 카테고리 리스트
    @Override
    public List getCateList(Criteria cri) {

        return session.selectList(namespace+"getCateList", cri);

    }

    // 카테고리 정보(+검색대상 갯수)
    @Override
    public CateFilterDTO getCateInfo(Criteria cri) {

        return session.selectOne(namespace+"getCateInfo", cri);

    }

    // 상품 정보
    @Override
    public BookVO getGoodsInfo(int bookId) {

        return session.selectOne(namespace+"getGoodsInfo", bookId);

    }

    // 상품 id 이름
    @Override
    public BookVO getBookIdName(int bookId) {

        return session.selectOne(namespace+"getBookIdName", bookId);

    }


}
