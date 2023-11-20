package com.book.service;

import com.book.model.Criteria;
import com.book.model.ReplyDTO;
import com.book.model.ReplyPageDTO;

public interface ReplyService {

    // 댓글 등록
    int enrollReply(ReplyDTO replyDTO);

    // 댓글 존재 체크
    String checkReply(ReplyDTO replyDTO);

    // 댓글 페이징
    ReplyPageDTO replyList(Criteria cri);



}
