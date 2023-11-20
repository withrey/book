package com.book.service;

import com.book.dao.ReplyDao;
import com.book.model.Criteria;
import com.book.model.PageDTO;
import com.book.model.ReplyDTO;
import com.book.model.ReplyPageDTO;
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

}
