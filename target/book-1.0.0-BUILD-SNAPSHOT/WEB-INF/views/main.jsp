<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>book</title>
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

</head>
<body>

<div class="wrapper">

    <div class="wrap">
        <div class="top_gnb_area">
            <ul class="list">
                <%-- 로그인 되어있지 않을 시 --%>
                <c:if test="${member == null}">
                    <li>
                        <a href="<c:url value='/member/login'/>">로그인</a>
                    </li>
                    <li>
                        <a href="<c:url value='/member/join'/>">회원가입</a>
                    </li>
                    <li>
                        고객센터
                    </li>
                </c:if>
                <%-- 로그인 시 --%>
                <c:if test="${member != null}">
                    <%-- 관리자 로그인 시 --%>
                    <c:if test="${member.adminCk == 1}">
                        <li>
                            <a href="<c:url value='/admin/main'/>">관리자 페이지</a>
                        </li>
                    </c:if>
                    <li>
                        <a id="gnb_logout_button">로그아웃</a>
                    </li>
                    <li>
                        마이룸
                    </li>
                    <li>
                        <a href="<c:url value='/cart/${member.memId}'/>">장바구니</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="top_area">
            <div class="logo_area">
                <a href="<c:url value='/main'/>"><img src='<c:url value='/img/mLogo.png'/>'/></a>
            </div>

            <div class="search_area">

                <div class="search_wrap">
                    <form id="searchForm" action="<c:url value='/search'/>" method="get">
                        <div class="search_input">
                            <select name="type">
                                <option value="T">책 제목</option>
                                <option value="A">작가</option>
                            </select>
                            <input type="text" name="keyword">
                            <button class="btn search_btn">검 색</button>
                        </div>
                    </form>
                </div>

            </div>

            <div class="login_area">

                <%-- 로그인 하지 않은 상태 --%>
                <c:if test="${member == null}">
                    <div class="login_button"><a href="<c:url value='/member/login'/>">로그인</a></div>
                    <span><a href="<c:url value='/member/join'/>">회원가입</a></span>
                </c:if>

                <%-- 로그인 한 상태 --%>
                <c:if test="${member != null}">
                    <div class="login_success_area">
                        <span>회원 : ${member.memName}</span>
                        <span>장바구니</span>
                        <a href="<c:url value='/member/logout'/>">로그아웃</a>
                    </div>
                </c:if>

            </div>
            <div class="clearfix"></div>
        </div>
        <div class="navi_bar_area">
            <div class="navi_bar_area">
                <div class="dropdown">
                    <button class="dropbtn">국내
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <c:forEach items="${cate1}" var="cate">
                            <a href="${pageContext.request.contextPath}/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="dropdown">
                    <button class="dropbtn">국외
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <c:forEach items="${cate2}" var="cate">
                            <a href="<c:url value='/search?type=C&cateCode=${cate.cateCode}'/>">${cate.cateName}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="content_area">
            <h1>content area</h1>
        </div>

        <!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
                <ul>
                    <li>회사소개</li>
                    <span class="line">|</span>
                    <li>이용약관</li>
                    <span class="line">|</span>
                    <li>고객센터</li>
                    <span class="line">|</span>
                    <li>광고문의</li>
                    <span class="line">|</span>
                    <li>채용정보</li>
                    <span class="line">|</span>
                </ul>
            </div>
        </div> <!-- class="footer_nav" -->

        <div class="footer">
            <div class="footer_container">

                <div class="footer_left">
                    <img src="<c:url value='/img/Logo.png'/>">
                </div>
                <div class="footer_right">
                    (주) Book    대표이사 : OOO
                    <br>
                    사업자등록번호 : ooo-oo-ooooo
                    <br>
                    대표전화 : oooo-oooo(발신자 부담전화)
                    <br>
                    <br>
                    COPYRIGHT(C) <strong>https://rireych.tistory.com/</strong>    ALL RIGHTS RESERVED.
                </div>
                <div class="clearfix"></div>
            </div>
        </div> <!-- class="footer" -->

    </div>
</div>

<script>
$(document).ready(function (){

    // 회원가입 성공
    let join_result = '${join_result}';

    if(join_result == 1){
        alert("회원가입 완료");
    }

});

// gnb_area 로그아웃 버튼 작동
$("#gnb_logout_button").click(function (){

    $.ajax({
        type : "post",
        url : "<c:url value='/member/logout'/>",
        success : function (data){
            alert("로그아웃 성공");
            // 페이지 새로 고침
            document.location.reload();
        }
    });

});

</script>

</body>
</html>
