package com.book.dao;

import com.book.model.ReplyDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyDaoImplTest {

    @Autowired
    private ReplyDao replyDao;

    @Test
    public void enrollReplyTest() {

        String id = "2222";
        int bookId = 964;
        double rating = 3.5;
        String content = "댓글 테스트";

        ReplyDTO dto = new ReplyDTO();
        dto.setBookId(bookId);
        dto.setMemId(id);
        dto.setRating(rating);
        dto.setContent(content);

        replyDao.enrollReply(dto);

    }
}