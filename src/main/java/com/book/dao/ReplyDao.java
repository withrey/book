package com.book.dao;

import com.book.model.Criteria;
import com.book.model.ReplyDTO;
import com.book.model.UpdateReplyDTO;
import org.apache.ibatis.annotations.Update;

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

    // 댓글 수정
    int updateReply(ReplyDTO dto);

    // 댓글 한개 정보(수정 페이지)
    ReplyDTO getUpdateReply(int replyId);

    // 댓글 삭제
    int deleteReply(int replyId);

    // 평점 평균 구하기
    Double getRatingAverage(int bookId);

    // 평점 평균 반영
    int updateRating(UpdateReplyDTO dto);

}
