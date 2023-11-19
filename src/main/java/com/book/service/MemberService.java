package com.book.service;

import com.book.model.MemberVO;

public interface MemberService {

    // 회원가입
    int memberJoin(MemberVO member) throws Exception;

    // 아이디 중복 검사
    int idCheck(String memId) throws Exception;

    // 로그인
    MemberVO memberLogin(MemberVO member) throws Exception;

    // 주문자 정보
    MemberVO getMemberInfo(String memId);

}
