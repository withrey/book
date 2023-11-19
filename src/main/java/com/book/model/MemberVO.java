package com.book.model;

public class MemberVO {

    // 아이디
    private String memId;

    // 비밀번호
    private String memPw;

    // 이름
    private String memName;

    // 생일
    private String memBirth;

    // 이메일
    private String memEmail;

    // 주소1
    private String memAddr1;

    // 주소2
    private String memAddr2;

    // 주소3
    private String memAddr3;

    // 관리자 여부 체크
    // 1이면 관리자, 0이면 일반 유저
    private int adminCk;

    // 가입일
    private int regDate;

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPw() {
        return memPw;
    }

    public void setMemPw(String memPw) {
        this.memPw = memPw;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getMemBirth() {
        return memBirth;
    }

    public void setMemBirth(String memBirth) {
        this.memBirth = memBirth;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemAddr1() {
        return memAddr1;
    }

    public void setMemAddr1(String memAddr1) {
        this.memAddr1 = memAddr1;
    }

    public String getMemAddr2() {
        return memAddr2;
    }

    public void setMemAddr2(String memAddr2) {
        this.memAddr2 = memAddr2;
    }

    public String getMemAddr3() {
        return memAddr3;
    }

    public void setMemAddr3(String memAddr3) {
        this.memAddr3 = memAddr3;
    }

    public int getAdminCk() {
        return adminCk;
    }

    public void setAdminCk(int adminCk) {
        this.adminCk = adminCk;
    }

    public int getRegDate() {
        return regDate;
    }

    public void setRegDate(int regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "memId='" + memId + '\'' +
                ", memPw='" + memPw + '\'' +
                ", memName='" + memName + '\'' +
                ", memBirth='" + memBirth + '\'' +
                ", memEmail='" + memEmail + '\'' +
                ", memAddr1='" + memAddr1 + '\'' +
                ", memAddr2='" + memAddr2 + '\'' +
                ", memAddr3='" + memAddr3 + '\'' +
                ", adminCk=" + adminCk +
                ", regDate=" + regDate +
                '}';
    }
}
