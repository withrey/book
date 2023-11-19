package com.book.dao;

import com.book.model.AttachImageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachDaoImpl implements AttachDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.AttachMapper.";

    // 이미지 데이터 반환
    public List<AttachImageVO> getAttachList(int bookId) {

        return session.selectList(namespace+"getAttachList", bookId);

    }


}
