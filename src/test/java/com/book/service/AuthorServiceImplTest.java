package com.book.service;

import com.book.model.AuthorVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceImplTest {

    @Autowired
    private AuthorService service;

    // 작가 등록
    @Test
    public void authorEnrollTest() {

        AuthorVO author = new AuthorVO();

        author.setAuthorName("테스트1");
        author.setNationId("01");
        author.setAuthorIntro("테스트 소개1");

        service.authorEnroll(author);

    }


    // 작가 상세 페이지
    @Test
    public void authorGetDetailTest() throws Exception {

        int authorId = 30;

        System.out.println("author....." + service.authorGetDetail(authorId));

    }

}