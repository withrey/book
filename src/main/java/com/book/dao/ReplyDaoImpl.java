package com.book.dao;

import com.book.model.ReplyDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDaoImpl implements ReplyDao{

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.ReplyMapper.";

    // 댓글 등록
    public int enrollReply(ReplyDTO replyDTO) {

        return session.insert(namespace+"enrollReply", replyDTO);

    }

}
