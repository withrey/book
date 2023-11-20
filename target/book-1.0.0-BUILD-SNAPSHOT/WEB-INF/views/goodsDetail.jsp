<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>book</title>
    <link rel="stylesheet" href="<c:url value='/css/goodsDetail.css'/>">

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
                        <a href='<c:url value="/cart/${member.memId}"/>'>장바구니</a>
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
                            <input type="text" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>">
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

        <div class="content_area">

            <div class="line">
            </div>
            <div class="content_top">
                <div class="ct_left_area">
                    <div class="image_wrap" data-bookid="${goodsInfo.imageList[0].bookId}" data-path="${goodsInfo.imageList[0].uploadPath}" data-uuid="${goodsInfo.imageList[0].uuid}" data-filename="${goodsInfo.imageList[0].fileName}">
                        <img>
                    </div>
                </div>
                <div class="ct_right_area">
                    <div class="title">
                        <h1>
                            ${goodsInfo.bookName}
                        </h1>
                    </div>
                    <div class="line">
                    </div>
                    <div class="author">
						 <span>
						 	${goodsInfo.authorName} 지음
						 </span>
                        <span>|</span>
                        <span>
                            ${goodsInfo.bookPuble}
                        </span>
                        <span>|</span>
                        <span class="bookDate">
                            ${goodsInfo.bookDate}
                        </span>
                    </div>
                    <div class="line">
                    </div>
                    <div class="price">
                        <div class="sale_price">정가 : <fmt:formatNumber value="${goodsInfo.bookPrice}" pattern="#,### 원" /></div>
                        <div class="discount_price">
                            판매가 : <span class="discount_price_number"><fmt:formatNumber value="${goodsInfo.bookPrice - (goodsInfo.bookPrice*goodsInfo.bookDiscount)}" pattern="#,### 원" /></span>
                            [<fmt:formatNumber value="${goodsInfo.bookDiscount*100}" pattern="###" />%
                            <fmt:formatNumber value="${goodsInfo.bookPrice*goodsInfo.bookDiscount}" pattern="#,### 원" /> 할인]</div>
                    </div>
                    <div class="line">
                    </div>
                    <div class="button">
                        <div class="button_quantity">
                            주문수량
                            <input type="text" class="quantity_input" value="1">
                            <span>
								<button class="plus_btn">+</button>
								<button class="minus_btn">-</button>
							</span>
                        </div>
                        <div class="button_set">
                            <a class="btn_cart">장바구니 담기</a>
                            <a class="btn_buy">바로구매</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="line">
            </div>
            <div class="content_middle">
                <div class="book_info">
                    ${goodsInfo.bookInfo}
                </div>

            </div>
            <div class="line">
            </div>
            <div class="content_bottom">
                <div class="reply_subject">
                    <h2>리뷰</h2>
                </div>

                <c:if test="${member != null}">
                    <div class="reply_button_wrap">
                        <button>리뷰 쓰기</button>
                    </div>
                </c:if>

                <div class="reply_not_div">

                </div>

                <ul class="reply_content_ul">

                    <%--<li>
                        <div class="comment_wrap">
                            <div class="reply_top">
                                <span class="id_span">admin</span>
                                <span class="date_span">2023-08-21</span>
                                <span class="rating_span">평점 : <span class="rating_value_span">4</span>점</span>
                                <a class="update_reply_btn">수정</a><a class="delete_reply_btn">삭제</a>
                            </div>
                            <div class="reply_bottom">
                                <div class="reply_bottom_txt">
                                    테스트입니다. 한번에 잘 되기를...
                                </div>
                            </div>
                        </div>
                    </li>--%>

                </ul>

                <div class="reply_pageInfo_div">
                    <ul class="pageMaker">

                    </ul>
                </div>

            </div>

            <%-- 주문 --%>
            <form action="<c:url value='/order/${member.memId}'/>" method="get" class="order_form">
                <input type="hidden" name="orders[0].bookId" value="${goodsInfo.bookId}">
                <input type="hidden" name="orders[0].bookCount" value="">
            </form>

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

    // 이미지 삽입
    const bobj = $(".image_wrap");
    // 이미지가 있는 경우
    if(bobj.data("bookid")) {

        const uploadPath = bobj.data("path");
        const uuid = bobj.data("uuid");
        const fileName = bobj.data("filename");

        const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
        bobj.find("img").attr('src', '<c:url value="/display?fileName="/>' + fileCallPath);

    // 이미지가 없는 경우
    } else {

        bobj.find("img").attr('src', '<c:url value="/img/goodsNoImage.png"/>');

    }


    // 출간일
    const year = "${goodsInfo.bookDate}";

    let tempYear = year.substr(0,10);
    let yearArray = tempYear.split("-")
    let bookDate = yearArray[0] + "년 " + yearArray[1] + "월 " + yearArray[2] + "일";

    $(".bookDate").html(bookDate);


    // 리뷰 댓글 리스트 출력
    const bookId = '${goodsInfo.bookId}';

    $.getJSON('<c:url value="/reply/list"/>', {bookId : bookId}, function (obj){

        makeReplyContent(obj);

    });

});

// 수량 버튼 조작
let quantity = $(".quantity_input").val();
$(".plus_btn").on("click", function (){
    $(".quantity_input").val(++quantity);
});
$(".minus_btn").on("click", function (){
    if(quantity > 1) {
        $(".quantity_input").val(--quantity);
    }
});

// 서버로 전송할 데이터
const form = {
    memId : '${member.memId}',
    bookId : '${goodsInfo.bookId}',
    bookCount : ''
}

// 장바구니 추가 버튼
$(".btn_cart").on("click", function (e){
    form.bookCount = $(".quantity_input").val();
    $.ajax({
        url : '<c:url value="/cart/add"/>',
        type : 'POST',
        data : form,
        success : function (result) {
            cartAlert(result);
        }
    })
});

function cartAlert(result) {
    if(result == '0') {
        alert("장바구니에 추가를 하지 못하였습니다.");
    } else if(result == '1') {
        alert("장바구니에 추가되었습니다.");
    } else if(result == '2') {
        alert("장바구니에 이미 추가되어져 있습니다.");
    } else if(result == '5') {
        alert("로그인이 필요합니다.");
    }
}


// 바로구매 버튼
$(".btn_buy").on("click", function (){

    let bookCount = $(".quantity_input").val();
    $(".order_form").find("input[name='orders[0].bookCount']").val(bookCount);
    $(".order_form").submit();

});


// 리뷰쓰기
$(".reply_button_wrap").on("click", function (e){

    e.preventDefault();

    const memId = '${member.memId}';
    const bookId = '${goodsInfo.bookId}';

    $.ajax({
        data: {
            bookId : bookId,
            memId : memId
        },
        url: '<c:url value="/reply/check"/>',
        type: 'POST',
        success: function (result){
            if(result === '1'){

                alert("이미 등록된 리뷰가 존재합니다")

            } else if(result === '0'){

                let popUrl = "<c:url value='/replyEnroll/'/>" + memId + "?bookId=" + bookId;
                console.log(popUrl);
                let popOption = "width=490px, height=490px, top=300px, left=300px, scrollbars=yes";

                // window(url, 팝업창 이름, 팝업창 관련 설정(크기, 스크롤방식 등))
                window.open(popUrl, "리뷰 쓰기", popOption);

            }
        }
    });
});


// 댓글 페이지 정보
const cri = {
    bookId : '${goodsInfo.bookId}',
    pageNum : 1,
    amount : 10
}


// 댓글 페이지 이동 버튼 동작
$(document).on('click', '.pageMaker_btn a', function (e){

    e.preventDefault();

    let page = $(this).attr("href");
    cri.pageNum = page;

    replyListInit();

});


// 댓글 데이터 서버 요청 및 댓글 동적 생성 메서드
let replyListInit = function (){
    $.getJSON('<c:url value="/reply/list"/>', cri, function (obj){

        makeReplyContent(obj);

    });
}


// 댓글(리뷰) 동적 생성 메서드
function makeReplyContent(obj){

    // makeReplyContent(obj);
    if(obj.list.length === 0) {

        $(".reply_not_div").html('<span>리뷰가 없습니다.</span>');
        $(".reply_content_ul").html('');
        $(".pageMaker").html('');

    } else {

        $(".reply_not_div").html('');
        const list = obj.list;
        const pf = obj.pageInfo;
        const userId = '${member.memId}';

        // 리스트
        let reply_list = '';


        $(list).each(function(i,obj){
            reply_list += '<li>';
            reply_list += '<div class="comment_wrap">';
            reply_list += '<div class="reply_top">';
            // 아이디
            reply_list += '<span class="id_span">'+ obj.memId+'</span>';
            // 날짜
            reply_list += '<span class="date_span">'+ obj.regDate +'</span>';
            // 평점
            reply_list += '<span class="rating_span">평점 : <span class="rating_value_span">'+ obj.rating +'</span>점</span>';
            if(obj.memId === userId){
                reply_list += '<a class="update_reply_btn" href="'+ obj.replyId +'">수정</a><a class="delete_reply_btn" href="'+ obj.replyId +'">삭제</a>';
            }
            reply_list += '</div>';
            reply_list += '<div class="reply_bottom">';
            reply_list += '<div class="reply_bottom_txt">'+ obj.content +'</div>';
            reply_list += '</div>';
            reply_list += '</div>';
            reply_list += '</li>';
        });

        $(".reply_content_ul").html(reply_list);

        // 페이지 버튼
        let reply_pageMaker = '';

        // 이전
        if(pf.prev){
            let prev_num = pf.pageStart -1;
            reply_pageMaker += '<li class="pageMaker_btn prev">';
            reply_pageMaker += '<a href="'+ prev_num +'">이전</a>';
            reply_pageMaker += '</li>';
        }

        // number btn
        for(let i = pf.pageStart; i < pf.pageEnd+1; i++){
            reply_pageMaker += '<li class="pageMaker_btn ';
            if(pf.cri.pageNum === i){
                reply_pageMaker += 'active';
            }
            reply_pageMaker += '">';
            reply_pageMaker += '<a href="'+i+'">'+i+'</a>';
            reply_pageMaker += '</li>';
        }

        // 다음
        if(pf.next){
            let next_num = pf.pageEnd + 1;
            reply_pageMaker += '<li class="pageMaker_btn next">';
            reply_pageMaker += '<a href="'+ next_num +'">다음</a>';
            reply_pageMaker += '</li>';
        }

        $(".pageMaker").html(reply_pageMaker);

    }
}


</script>

</body>
</html>
