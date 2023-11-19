package com.book.dao;

import com.book.model.MemberVO;

public interface MemberDao {

    // 회원 가입
    int memberJoin(MemberVO member);

    // 아아디 중복 검사
    int idCheck(String memId);

    // 로그인
    MemberVO memberLogin(MemberVO member);

    // 주문자 주소 정보
    MemberVO getMemberInfo(String memId);

}
