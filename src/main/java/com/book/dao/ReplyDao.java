package com.book.dao;

import com.book.model.ReplyDTO;

public interface ReplyDao {

    // 댓글 등록
    int enrollReply(ReplyDTO replyDTO);

}
