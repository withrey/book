package com.book.dao;

import com.book.model.AuthorVO;
import com.book.model.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;


    // 작가 등록 테스트
    @Test
    public void authorEnrollTest() {

        AuthorVO author = new AuthorVO();

        author.setAuthorName("테스트");
        author.setNationId("01");
        author.setAuthorIntro("테스트 소개");

        authorDao.authorEnroll(author);

    }


    // 작가 목록 테스트
    @Test
    public void authorGetListTest() {

        Criteria cri = new Criteria(3,10);

        List<AuthorVO> list = authorDao.authorGetList(cri);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list" + i + "....." + list.get(i));
        }
    }
    @Test
    public void authorGetListTest1() {

        Criteria cri = new Criteria(3,10);
        cri.setKeyword("이말년");

        List<AuthorVO> list = authorDao.authorGetList(cri);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("list" + i + "....." + list.get(i));
        }
    }


    // 작가 수
    @Test
    public void authorGetTotalTest() {
        
        Criteria cri = new Criteria();
        cri.setKeyword("이말년");
        
        int total = authorDao.authorGetTotal(cri);

        System.out.println("total = " + total);
        
    }


    // 작가 상세 페이지
    @Test
    public void authorGetDetailTest() {

        int authorId = 30;

        AuthorVO author = authorDao.authorGetDetail(authorId);

        System.out.println("author......" + author);

    }


    // 작가 정보 수정
    @Test
    public void authorModifyTest() {

        AuthorVO author = new AuthorVO();

        author.setAuthorId(1);
        System.out.println("수정 전....." + authorDao.authorGetDetail(author.getAuthorId()));

        author.setAuthorName("수정");
        author.setNationId("01");
        author.setAuthorIntro("소개 수정");

        authorDao.authorModify(author);
        System.out.println("수정 후....." + authorDao.authorGetDetail(author.getAuthorId()));

    }

    // 작가 정보 삭제
    @Test
    public void authorDeleteTest() {

        int authorId = 2;

        int result = authorDao.authorDelete(authorId);

        if(result == 1) {
            System.out.println("삭제 성공");
        }

    }

}