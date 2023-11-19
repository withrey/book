package com.book.service;

import com.book.dao.MemberDao;
import com.book.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDao memberDao;

    // 회원가입
    @Override
    public int memberJoin(MemberVO member) throws Exception {

        return memberDao.memberJoin(member);

    }

    // 아이디 중복 검사
    @Override
    public int idCheck(String memId) throws Exception {

        return memberDao.idCheck(memId);

    }

    // 로그인
    @Override
    public MemberVO memberLogin(MemberVO member) throws Exception {

        return memberDao.memberLogin(member);

    }

    // 주문자 정보
    @Override
    public MemberVO getMemberInfo(String memId) {

        return memberDao.getMemberInfo(memId);

    }

}
