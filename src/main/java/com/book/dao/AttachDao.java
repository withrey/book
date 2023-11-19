package com.book.dao;

import com.book.model.AttachImageVO;

import java.util.List;

public interface AttachDao {

    // 이미지 데이터 반환
    List<AttachImageVO> getAttachList(int bookId);

}
