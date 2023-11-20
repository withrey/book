package com.book.dao;

import com.book.model.Criteria;
import com.book.model.ReplyDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDaoImpl implements ReplyDao{

    @Autowired
    private SqlSession session;

    private static String namespace = "com.book.mapper.ReplyMapper.";

    // 댓글 등록
    public int enrollReply(ReplyDTO replyDTO) {

        return session.insert(namespace+"enrollReply", replyDTO);

    }

    // 댓글 존재 체크
    public Integer checkReply(ReplyDTO replyDTO) {

        return session.selectOne(namespace+"checkReply", replyDTO);

    }

    // 댓글 페이징
    public List<ReplyDTO> getReplyList(Criteria cri) {

        return session.selectList(namespace+"getReplyList" ,cri);

    }

    // 댓글 총 갯수(페이징)
    public int getReplyTotal(int bookId) {

        return session.selectOne(namespace+"getReplyTotal", bookId);

    }

}
