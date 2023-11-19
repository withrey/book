package com.book.service;

import com.book.model.AttachImageVO;

import java.util.List;

public interface AttachService {

    // 이미지 데이터 반환
    List<AttachImageVO> getAttachList(int bookId);

}
