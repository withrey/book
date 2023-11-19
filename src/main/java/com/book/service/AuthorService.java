package com.book.service;

import com.book.model.AuthorVO;
import com.book.model.Criteria;

import java.util.List;

public interface AuthorService {

    // 작가 등록
    int authorEnroll(AuthorVO author);

    // 작가 목록
    List<AuthorVO> authorGetList(Criteria cri) throws Exception;

    // 작가 수
    int authorGetTotal(Criteria cri) throws Exception;

    // 작가 상세 페이지
    AuthorVO authorGetDetail(int authorId) throws Exception;

    // 작가 정보 수정
    int authorModify(AuthorVO author) throws Exception;

    // 작가 정보 삭제
    int authorDelete(int authorId);

}
