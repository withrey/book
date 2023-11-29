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
    <%-- slick 기본 css --%>
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <%-- slick 테마 css --%>
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <%-- slick js --%>
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

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
                            <input type="text" name="keyword" placeholder="키워드를 입력해주세요">
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
                        <span><a href="<c:url value='/cart/${member.memId}'/>">장바구니</a></span>
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
            <%-- 슬라이드 배너 --%>
            <div class="slide_div_wrap">
                <div class="slide_div">
                    <div>
                        <a>
                            <img src="<c:url value='/img/banner1.jpg'/>">
                        </a>
                    </div>
                    <div>
                        <a>
                            <img src="<c:url value='/img/banner2.jpg'/>">
                        </a>
                    </div>
                    <div>
                        <a>
                            <img src="<c:url value='/img/banner3.jpg'/>">
                        </a>
                    </div>
                    <div>
                        <a>
                            <img src="<c:url value='/img/banner4.jpg'/>">
                        </a>
                    </div>
                </div>
            </div>

            <div class="ls_wrap">
                <div class="ls_div_subject">
                    평점순 상품
                </div>
                <div class="ls_div">
                    <c:forEach items="${ls}" var="ls">
                        <a href="<c:url value='/goodsDetail/${ls.bookId}'/>">
                            <div class="ls_div_content_wrap">
                                <div class="ls_div_content">
                                    <div class="image_wrap" data-bookid="${ls.imageList[0].bookId}" data-path="${ls.imageList[0].uploadPath}" data-uuid="${ls.imageList[0].uuid}" data-filename="${ls.imageList[0].fileName}">
                                        <img>
                                    </div>
                                    <div class="ls_category">
                                        ${ls.cateName}
                                    </div>
                                    <div class="ls_rating">
                                        ${ls.ratingAvg}
                                    </div>
                                    <div class="ls_bookName">
                                        ${ls.bookName}
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>

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

    // 평점순 정렬
    $(".ls_div").slick({

        slidesToShow: 4,
        slidesToScroll: 4,
        prevArrow: "<button type'button' class='ls_div_content_prev'>이전</button>",
        nextArrow: "<button type'button' class='ls_div_content_next'>다음</button>"

    });


    // 배너 움직임
    $(".slide_div").slick(
        {
            dots: true,
            autoplay: true,
            // 배너 넘어가는 속도 - 2000은 2초
            autoplaySpeed: 2000
        }
    );


    // 이미지 삽입
    $(".image_wrap").each(function(i, obj){

        const bobj = $(obj);

        if(bobj.data("bookid")){

            const uploadPath = bobj.data("path");
            const uuid = bobj.data("uuid");
            const fileName = bobj.data("filename");

            const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);

            $(this).find("img").attr('src', '<c:url value="/display?fileName="/>' + fileCallPath);

        } else {

            $(this).find("img").attr('src', '<c:url value="/img/goodsNoImage.png"/>');

        }

    });


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
