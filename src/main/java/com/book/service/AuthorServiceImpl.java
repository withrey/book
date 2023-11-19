package com.book.service;

import com.book.dao.AuthorDao;
import com.book.model.AuthorVO;
import com.book.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDao authorDao;

    // 작가 등록
    @Override
    public int authorEnroll(AuthorVO author) {

        return authorDao.authorEnroll(author);

    }

    // 작가 목록
    @Override
    public List<AuthorVO> authorGetList(Criteria cri) throws Exception {

        return authorDao.authorGetList(cri);

    }

    // 작가 수
    @Override
    public int authorGetTotal(Criteria cri) throws Exception {

        return authorDao.authorGetTotal(cri);

    }

    // 작가 상세 페이지
    @Override
    public AuthorVO authorGetDetail(int authorId) throws Exception {

        return authorDao.authorGetDetail(authorId);

    }

    // 작가 정보 수정
    @Override
    public int authorModify(AuthorVO author) throws Exception {

        return authorDao.authorModify(author);

    }

    // 작가 정보 삭제
    @Override
    public int authorDelete(int authorId) {

        return authorDao.authorDelete(authorId);

    }

}
