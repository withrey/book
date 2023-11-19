package com.book.dao;

import com.book.model.AuthorVO;
import com.book.model.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "com.book.mapper.AuthorMapper.";

    // 작가 등록
    public int authorEnroll(AuthorVO author) {

        return session.insert(namespace+"authorEnroll", author);

    }

    // 작가 목록
    @Override
    public List<AuthorVO> authorGetList(Criteria cri) {

        return session.selectList(namespace+"authorGetList", cri);

    }

    // 작가 수
    @Override
    public int authorGetTotal(Criteria cri) {

        return session.selectOne(namespace+"authorGetTotal", cri);

    }

    // 작가 상세
    @Override
    public AuthorVO authorGetDetail(int authorId) {

        return session.selectOne(namespace+"authorGetDetail", authorId);

    }

    // 작가 정보 수정
    @Override
    public int authorModify(AuthorVO author) {

        return session.update(namespace+"authorModify", author);

    }

    // 작가 정보 삭제
    @Override
    public int authorDelete(int authorId) {

        return session.delete(namespace+"authorDelete", authorId);

    }

}
