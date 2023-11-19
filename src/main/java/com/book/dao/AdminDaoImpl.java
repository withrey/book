package com.book.dao;

import com.book.model.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.AdminMapper.";

    // 책 등록
    public int bookEnroll(BookVO book) {

        return session.insert(namespace+"bookEnroll", book);

    }

    // 카테고리 리스트
    @Override
    public List<CateVO> cateList() {

        return session.selectList(namespace+"cateList");

    }

    // 상품 리스트
    @Override
    public List<BookVO> goodsGetList(Criteria cri) {

        return session.selectList(namespace+"goodsGetList", cri);

    }

    // 상품 총 개수
    @Override
    public int goodsGetTotal(Criteria cri) {

        return session.selectOne(namespace+"goodsGetTotal", cri);

    }

    // 상품 조회 페이지
    @Override
    public BookVO goodsGetDetail(int bookId) {

        return session.selectOne(namespace+"goodsGetDetail", bookId);

    }

    // 상품 수정
    @Override
    public int goodsModify(BookVO book) {

        return session.update(namespace+"goodsModify",book);

    }

    // 상품 삭제
    @Override
    public int goodsDelete(int bookId) {

        return session.delete(namespace+"goodsDelete", bookId);

    }

    // 이미지 등록
    @Override
    public int imageEnroll(AttachImageVO imageVO) {

        return session.insert(namespace+"imageEnroll", imageVO);

    }

    // 지정 상품 이미지 전체 삭제
    @Override
    public int deleteImageAll(int bookId) {

        return session.delete(namespace+"deleteImageAll", bookId);

    }

    // 어제자 날짜 이미지 리스트
    @Override
    public List<AttachImageVO> checkFileList() {

        return session.selectList(namespace+"checkFileList");

    }

    // 지정 상품 이미지 정보 얻기
    @Override
    public List<AttachImageVO> getAttachInfo(int bookId) {

        return session.selectList(namespace+"getAttachInfo", bookId);

    }

    // 주문 상품 리스트
    @Override
    public List<OrderDTO> getOrderList(Criteria cri) {

        return session.selectList(namespace+"getOrderList", cri);

    }

    // 주문 총 갯수
    @Override
    public int getOrderTotal(Criteria cri) {

        return session.selectOne(namespace+"getOrderTotal", cri);

    }


}
