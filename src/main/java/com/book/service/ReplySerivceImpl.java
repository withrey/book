package com.book.service;

import com.book.dao.ReplyDao;
import com.book.model.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplySerivceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;


    // 댓글 등록
    @Override
    public int enrollReply(ReplyDTO replyDTO) {

        int result = replyDao.enrollReply(replyDTO);

        return result;

    }


}
