package com.book.dao;

import com.book.model.Criteria;
import com.book.model.ReplyDTO;

import java.util.List;

public interface ReplyDao {

    // 댓글 등록
    int enrollReply(ReplyDTO replyDTO);

    // 댓글 존재 체크
    Integer checkReply(ReplyDTO replyDTO);

    // 댓글 페이징
    List<ReplyDTO> getReplyList(Criteria cri);

    // 댓글 총 갯수(페이징)
    int getReplyTotal(int bookId);

}
