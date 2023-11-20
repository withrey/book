<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>book</title>
    <link rel="stylesheet" href="<c:url value='/css/order.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

    <!-- 다음주소 -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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

            <div class="content_subject"><span>장바구니</span></div>

            <div class="content_main">
                <%-- 회원 정보 --%>
                <div class="member_info_div">
                    <table class="table_text_align_center memberInfo_table">
                        <tbody>
                            <tr>
                                <th style="width: 25%;">주문자</th>
                                <td style="width: *">${memberInfo.memName} | ${memberInfo.memEmail}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <%-- 배송지 정보 --%>
                <div class="addressInfo_div">
                    <div class="addressInfo_button_div">
                        <button class="address_btn address_btn_1" onclick="showAddress('1')" style="background-color: #3c3838;">사용자 정보 주소록</button>
                        <button class="address_btn address_btn_2" onclick="showAddress('2')">직접 입력</button>
                    </div>
                    <div class="addressInfo_input_div_wrap">
                        <div class="addressInfo_input_div addressInfo_input_div_1" style="display: block">
                            <table>
                                <colgroup>
                                    <col width="25%">
                                    <col width="*">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>이름</th>
                                        <td>
                                            ${memberInfo.memName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>주소</th>
                                        <td>
                                            <input class="selectAddress" type="hidden" value="T">
                                            ${memberInfo.memAddr1} ${memberInfo.memAddr2}<br>${memberInfo.memAddr3}
                                            <input class="addressee_input" type="hidden" value="${memberInfo.memName}">
                                            <input class="address_input_1" type="hidden" value="${memberInfo.memAddr1}">
                                            <input class="address_input_2" type="hidden" value="${memberInfo.memAddr2}">
                                            <input class="address_input_3" type="hidden" value="${memberInfo.memAddr3}">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="addressInfo_input_div addressInfo_input_div_2">
                            <table>
                                <colgroup>
                                    <col width="25%">
                                    <col width="*">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th>이름</th>
                                        <td>
                                            <input class="addressee_input">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>주소</th>
                                        <td>
                                            <input class="selectAddress" type="hidden" value="F">
                                            <input class="address_input_1" readonly="readonly"> <a class="address_search_btn" onclick="execution_daum_address()">주소 찾기</a><br>
                                            <input class="address_input_2" readonly="readonly"><br>
                                            <input class="address_input_3" readonly="readonly">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <%-- 상품 정보 --%>
                <div class="orderGoods_div">
                    <%-- 상품 종류 --%>
                    <div class="goods_kind_div">
                        주문상품<span class="goods_kind_div_kind"></span>종 <span class="goods_kind_div_count"></span>개
                    </div>
                    <%-- 상품 테이블 --%>
                    <table class="goods_subject_table">
                        <colgroup>
                            <col width="15%">
                            <col width="45%">
                            <col width="40%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>이미지</th>
                                <th>상품 정보</th>
                                <th>판매가</th>
                            </tr>
                        </tbody>
                    </table>
                    <table class="goods_table">
                        <colgroup>
                            <col width="15%">
                            <col width="45%">
                            <col width="40%">
                        </colgroup>
                        <tbody>
                            <c:forEach items="${orderList}" var="ol">
                                <tr>
                                    <td>
                                        <div class="image_wrap" data-bookid="${ol.imageList[0].bookId}" data-path="${ol.imageList[0].uploadPath}" data-uuid="${ol.imageList[0].uuid}" data-filename="${ol.imageList[0].fileName}">
                                            <img>
                                        </div>
                                    </td>
                                    <td>${ol.bookName}</td>
                                    <td class="goods_table_price_td">
                                        <fmt:formatNumber value="${ol.salePrice}" pattern="#,### 원" /> | 수량 ${ol.bookCount} 개<br>
                                        <fmt:formatNumber value="${ol.totalPrice}" pattern="#,### 원"/> <br>
                                        <input type="hidden" class="individual_bookPrice_input" value="${ol.bookPrice}">
                                        <input type="hidden" class="individual_salePrice_input" value="${ol.salePrice}">
                                        <input type="hidden" class="individual_bookCount_input" value="${ol.bookCount}">
                                        <input type="hidden" class="individual_totalPrice_input" value="${ol.salePrice * ol.bookCount}">
                                        <input type="hidden" class="individual_bookId_input" value="${ol.bookId}">
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <%-- 주문 요청 --%>
                    <form class="order_form" action="<c:url value='/order'/>" method="post">
                        <%-- 주문자 회원번호 --%>
                        <input type="hidden" name="memId" value="${memberInfo.memId}">
                        <%-- 주소록&받는이 --%>
                        <input type="hidden" name="addressee">
                        <input type="hidden" name="memAddr1">
                        <input type="hidden" name="memAddr2">
                        <input type="hidden" name="memAddr3">
                        <%-- 상품 정보 --%>
                    </form>

                </div>

                <%-- 주문 종합 정보 --%>
                <div class="total_info_div">
                    <%-- 가격 종합 정보 --%>
                    <div class="total_info_price_div">
                        <ul>
                            <li>
                                <span class="price_span_label">상품 금액</span>
                                <span class="totalPrice_span">10000</span>원
                            </li>
                            <li>
                                <span class="price_span_label">배송비</span>
                                <span class="delivery_price_span">10000</span>원
                            </li>
                            <li class="price_total_li">
                                <strong class="price_span_label total_price_label">최종 결제 금액</strong>
                                <strong class="strong_red">
                                    <span class="total_price_red finalTotalPrice_span">
                                        150000
                                    </span>원
                                </strong>
                            </li>
                        </ul>
                    </div>
                    <%-- 버튼 영역 --%>
                    <div class="total_info_btn_div">
                        <a class="order_btn">결제하기</a>
                    </div>
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

    // 주문 조합정보란 최신화
    setTotalInfo();

    // 이미지 삽입
    $(".image_wrap").each(function (i, obj){

        const bobj = $(obj);

        if(bobj.data("bookid")) {

            const uploadPath = bobj.data("path");
            const uuid = bobj.data("uuid");
            const fileName = bobj.data("filename");

            const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);

            $(this).find("img").attr('src', '<c:url value="/display?fileName="/>' + fileCallPath);
        } else {
            $(this).find("img").attr('src', '<c:url value="/img/goodsNoImage.png"/>');

        }

    });

});

<%-- 주소입력란 버튼 동작(숨김, 등장) --%>
function showAddress(className) {
    /* 컨텐츠 동작 */
    // 모두 숨기기
    $(".addressInfo_input_div").css("display","none");
    // 컨텐츠 보이기
    $(".addressInfo_input_div_" + className).css("display","block");

    /* 버튼 색생 변경 */
    // 모든 색상 동일
    $(".address_btn").css("backgroundColor","#555");
    // 지정 색상 변경
    $(".address_btn_" + className).css("backgroundColor", "#3c3838");

    /* selectAddress T/F */
    // 모든 selectAddress F 만들기
    $(".addressInfo_input_div").each(function (i, obj){
        $(obj).find(".selectAddress").val("F");
    });
    // 선택한 selectAddress T 만들기
    $(".addressInfo_input_div_" + className).find(".selectAddress").val("T");

}


// 다음 주소 연동
function execution_daum_address(){

    new daum.Postcode({
        oncomplete: function (data){
            // 팝업에서 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                // document.getElementById("sample6_extraAddress").value = extraAddr;
                addr += extraAddr;

            } else {
                // document.getElementById("sample6_extraAddress").value = '';
                addr += ' ';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            // document.getElementById('sample6_postcode').value = data.zonecode;
            $(".address_input_1").val(data.zonecode);
            // document.getElementById("sample6_address").value = addr;
            $(".address_input_2").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            // document.getElementById("sample6_detailAddress").focus();
            $(".address_input_3").attr("readonly",false);
            $(".address_input_3").focus();

        }
    }).open();
}


// 총 주문 정보 세팅(배송비, 총 가격, 물품 수, 종류)
function setTotalInfo(){

    // 총 가격
    let totalPrice = 0;

    // 총 갯수
    let totalCount = 0;

    // 총 종류
    let totalKind = 0;

    // 배송비
    let deliveryPrice = 0;

    // 최종 가격(총 가격 + 배송비)
    let finalTotalPrice = 0;

    $(".goods_table_price_td").each(function (index, element){

        // 총 가격
        totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());

        // 총 갯수
        totalCount += parseInt($(element).find(".individual_bookCount_input").val());

        // 총 종류
        totalKind += 1;

    });

    // 배송비 결정
    if(totalPrice >= 30000) {

        deliveryPrice = 0;

    } else if(totalPrice == 0) {

        deliveryPrice = 0;

    } else {

        deliveryPrice = 3000;

    }

    finalTotalPrice = totalPrice + deliveryPrice;

    /* 값 삽입 */
    // 총 가격
    $(".totalPrice_span").text(totalPrice.toLocaleString());

    // 총 갯수
    $(".goods_kind_div_count").text(totalCount);

    // 총 종류
    $(".goods_kind_div_kind").text(totalKind);

    // 배송비
    $(".delivery_price_span").text(deliveryPrice.toLocaleString());

    // 최종 가격(총 가격 + 배송비)
    $(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());

}

// 주문 요청
$(".order_btn").on("click", function (){
    // 주소 정보 & 받는 이
    $(".addressInfo_input_div").each(function (i, obj){
        if($(obj).find(".selectAddress").val() === 'T'){
            $("input[name='addressee']").val($(obj).find(".addressee_input").val());
            $("input[name='memAddr1']").val($(obj).find(".address_input_1").val());
            $("input[name='memAddr2']").val($(obj).find(".address_input_2").val());
            $("input[name='memAddr3']").val($(obj).find(".address_input_3").val());
        }
    });

    // 상품 정보
    let form_contents = '';
    $(".goods_table_price_td").each(function (index, element){
        let bookId = $(element).find(".individual_bookId_input").val();
        let bookCount = $(element).find(".individual_bookCount_input").val();
        let bookId_input = "<input name='orders[" + index + "].bookId' type='hidden' value='" + bookId + "'>";
        form_contents += bookId_input;
        let bookCount_input = "<input name='orders[" + index + "].bookCount' type='hidden' value='" + bookCount + "'>";
        form_contents += bookCount_input;
    });
    $(".order_form").append(form_contents);

    // 서버 전송
    $(".order_form").submit();

});


</script>

</body>
</html>
