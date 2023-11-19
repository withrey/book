package com.book.dao;

import com.book.model.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberDaoImplTest {

    @Autowired
    private MemberDao memberDao;


    // 회원가입
    @Test
    public void memberJoin() throws Exception {

        // 멤버 객체 생성
        MemberVO member = new MemberVO();

        // 테스트 값 입력
        member.setMemId("test11");
        member.setMemPw("test");
        member.setMemName("test");
        member.setMemBirth("2022-01-01");
        member.setMemEmail("test@test.com");
        member.setMemAddr1("test");
        member.setMemAddr2("test");
        member.setMemAddr3("test");

        // 회원가입 메서드
        memberDao.memberJoin(member);

    }

    // 아이디 중복 검사
    @Test
    public void idCheck() throws Exception {

        String id = "admin";
        String id2 = "admin123";
        memberDao.idCheck(id);
        memberDao.idCheck(id2);

    }

    // 로그인
    @Test
    public void memberLogin() throws Exception {

        MemberVO member = new MemberVO();

        member.setMemId("1111");
        member.setMemPw("0000");

        memberDao.memberLogin(member);
        System.out.println("결과 값 " + memberDao.memberLogin(member));

    }
}