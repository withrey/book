package com.book.service;

import com.book.dao.ReplyDao;
import com.book.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;


    // 댓글 등록
    @Override
    public int enrollReply(ReplyDTO replyDTO) {

        int result = replyDao.enrollReply(replyDTO);

        setRating(replyDTO.getBookId());

        return result;

    }

    // 댓글 존재 체크
    @Override
    public String checkReply(ReplyDTO replyDTO) {

        Integer result = replyDao.checkReply(replyDTO);

        if(result == null) {
            // 댓글 없음
            return "0";
        } else {
            // 댓글 있음
            return "1";
        }

    }

    // 댓글 페이징
    @Override
    public ReplyPageDTO replyList(Criteria cri) {

        ReplyPageDTO dto = new ReplyPageDTO();

        dto.setList(replyDao.getReplyList(cri));
        dto.setPageInfo(new PageDTO(cri, replyDao.getReplyTotal(cri.getBookId())));

        return dto;

    }

    // 댓글 수정
    @Override
    public int updateReply(ReplyDTO dto) {

        int result = replyDao.updateReply(dto);

        setRating(dto.getBookId());

        return result;

    }

    // 댓글 한개 정보(수정 페이지)
    @Override
    public ReplyDTO getUpdateReply(int replyId) {

        return replyDao.getUpdateReply(replyId);

    }

    // 댓글 삭제
    @Override
    public int deleteReply(ReplyDTO dto) {

        int result = replyDao.deleteReply(dto.getReplyId());

        setRating(dto.getBookId());

        return result;
    }

    // 댓글 평점
    public void setRating(int bookId) {

        Double ratingAvg = replyDao.getRatingAverage(bookId);

        if(ratingAvg == null) {
            ratingAvg = 0.0;
        }

        ratingAvg = (double)(Math.round(ratingAvg*10));
        ratingAvg = ratingAvg / 10;

        UpdateReplyDTO urd = new UpdateReplyDTO();
        urd.setBookId(bookId);
        urd.setRatingAvg(ratingAvg);

        replyDao.updateRating(urd);

    }
}
