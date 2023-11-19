package com.book.dao;

import com.book.model.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "com.book.mapper.MemberMapper.";

    // 회원가입
    public int memberJoin(MemberVO member) {

        return session.insert(namespace+"memberJoin", member);

    }

    // 아아디 중복 검사
    public int idCheck(String memId) {

        return session.selectOne(namespace+"idCheck", memId);

    }

    // 로그인
    public MemberVO memberLogin(MemberVO member) {

        return session.selectOne(namespace+"memberLogin", member);

    }

    // 주문자 주소 정보
    public MemberVO getMemberInfo(String memId) {

        return session.selectOne(namespace+"getMemberInfo", memId);

    }


}
