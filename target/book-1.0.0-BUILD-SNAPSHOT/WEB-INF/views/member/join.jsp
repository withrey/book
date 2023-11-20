<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="<c:url value='/css/member/join.css'/>">
    <%-- datepicker 위젯 1 --%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

    <%-- datepicker 위젯 2 --%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

</head>
<body>

<div class="wrapper">
    <form id="join_form" method="post">
        <div class="wrap">

            <div class="subjecet">
                <span>회원가입</span>
            </div>

            <div class="id_wrap">
                <div class="id_name">아이디</div>
                <div class="id_input_box">
                    <input class="id_input" name="memId">
                </div>
                <%-- 아이디 중복검사 메시지 --%>
                <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
                <span class="id_input_re_2">아이디가 이미 존재합니다.</span>
                <span class="final_id_ck">아이디를 입력해주세요.</span>
            </div>

            <div class="pw_wrap">
                <div class="pw_name">비밀번호</div>
                <div class="pw_input_box">
                    <input class="pw_input" name="memPw" type="password">
                </div>
                <span class="final_pw_ck">비밀번호를 입력해주세요.</span>
            </div>

            <div class="pwck_wrap">
                <div class="pwck_name">비밀번호 확인</div>
                <div class="pwck_input_box">
                    <input class="pwck_input" type="password">
                </div>
                <span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
                <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
            </div>

            <div class="user_wrap">
                <div class="user_name">이름</div>
                <div class="user_input_box">
                    <input class="user_input" name="memName">
                </div>
                <span class="final_name_ck">이름을 입력해주세요.</span>
            </div>

            <div class="birth_wrap">
                <div class="user_birth">생일</div>
                <div class="birth_input_box">
<%--                    <input class="birth_input" name="memBirth">--%>
                    <input class="birth_input" name="memBirth" autocomplete="off" readonly="readonly">
                </div>
                <span class="final_birth_ck">생일을 선택해주세요.</span>
            </div>

            <div class="mail_wrap">
                <div class="mail_name">이메일</div>
                <div class="mail_input_box">
                    <input class="mail_input" name="memEmail">
                </div>
                <span class="final_mail_ck">이메일을 입력해주세요.</span>
                <span class="mail_input_box_warn"></span>
                <div class="mail_check_wrap">
                    <div class="mail_check_input_box" id="mail_check_input_box_false">
                        <input class="mail_check_input" disabled="disabled">
                    </div>
                    <div class="mail_check_button">
                        <span>인증번호 전송</span>
                    </div>
                    <div class="clearfix"></div>
                    <span id="mail_check_input_box_warn"></span>
                </div>
            </div>

            <div class="address_wrap">
                <div class="address_name">주소</div>
                <div class="address_input_1_wrap">
                    <div class="address_input_1_box">
                        <input class="address_input_1" name="memAddr1" readonly="readonly">
                    </div>
                    <div class="address_button" onclick="execution_daum_address()">
                        <span>주소 찾기</span>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class ="address_input_2_wrap">
                    <div class="address_input_2_box">
                        <input class="address_input_2" name="memAddr2" readonly="readonly">
                    </div>
                </div>
                <div class ="address_input_3_wrap">
                    <div class="address_input_3_box">
                        <input class="address_input_3" name="memAddr3" readonly="readonly">
                    </div>
                </div>
                <span class="final_addr_ck">주소를 입력해주세요.</span>
            </div>

            <div class="join_button_wrap">
                <input type="button" class="join_button" value="가입하기">
            </div>
        </div>
    </form>
</div>

<%-- 다음 주소록 API --%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

// 이메일전송 인증번호 저장을 위한 코드
var code = "";


// 유효성 검사 통과유무 변수

// 아이디
var idCheck = false;
// 아이디 중복 검사
var idckCheck = false;
// 비번
var pwCheck = false;
// 비번 확인
var pwckCheck = false;
// 비번 확인 일치 확인
var pwckcorCheck = false;
// 이름
var nameCheck = false;
// 생일
var birthCheck = false;
// 이메일
var mailCheck = false;
// 이메일 인증번호 확인
var mailnumCheck = false;
// 주소
var addressCheck = false



$(document).ready(function (){

    // 회원가입 버튼(회원가입 기능 작동)
    $(".join_button").click(function (){
        // 입력값 변수

        // id 입력란
        var id = $('.id_input').val();
        // 비밀번호 입력란
        var pw = $('.pw_input').val();
        // 비밀번호 확인 입력란
        var pwck = $('.pwck_input').val();
        // 이름 입력란
        var name = $('.user_input').val();
        // 생일 입력란
        var birth = $('.birth_input').val();
        // 이메일 입력란
        var mail = $('.mail_input').val();
        // 주소 입력란
        var addr = $('.address_input_3').val();

        // 아이디 유효성 검사
        if(id == ""){
            $('.final_id_ck').css('display','block');
            idCheck = false;
        }else{
            $('.final_id_ck').css('display','none');
            idCheck = true;
        }

        // 비밀번호 유효성 검사
        if(pw == ""){
            $('.final_pw_ck').css('display','block');
            pwCheck = false;
        }else{
            $('.final_pw_ck').css('display','none');
            pwCheck = true;
        }

        // 비밀번호 확인 유효성 검사
        if(pwck == ""){
            $('.final_pwck_ck').css('display','block');
            pwckCheck = false;
        }else{
            $('.final_pwck_ck').css('display','none');
            pwckCheck = true;
        }

        // 이름 유효성 검사
        if(name == ""){
            $('.final_name_ck').css('display','block');
            nameCheck = false;
        }else{
            $('.final_name_ck').css('display', 'none');
            nameCheck = true;
        }

        // 생일 유효성 검사
        if(name == ""){
            $('.final_birth_ck').css('display','block');
            birthCheck = false;
        }else{
            $('.final_birth_ck').css('display', 'none');
            birthCheck = true;
        }

        // 이메일 유효성 검사
        if(mail == ""){
            $('.final_mail_ck').css('display','block');
            mailCheck = false;
        }else{
            $('.final_mail_ck').css('display','none');
            mailCheck = true;
        }

        // 주소 유효성 검사
        if(addr == ""){
            $('.final_addr_ck').css('display','block');
            addressCheck = false;
        }else{
            $('.final_addr_ck').css('display','none');
            addressCheck = true;
        }

        if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&birthCheck&&mailCheck&&mailnumCheck&&addressCheck){
            $("#join_form").attr("action", "<c:url value='/member/join'/>");
            $("#join_form").submit();
        }

        return false;
    });
});

// 아아디 중복 검사
$(".id_input").on("propertychange change keyup paste input", function (){

    // .id_input에 입력되는 값
    var memId = $(".id_input").val();
    var data = {memId : memId};

    $.ajax({
        type : "post",
        url : "<c:url value='/member/memberIdChk'/>",
        data : data,
        success : function (result) {
            if(result != 'fail'){
                $('.id_input_re_1').css("display","inline-block");
                $('.id_input_re_2').css("display","none");
                idckCheck = true;
            } else {
                $('.id_input_re_2').css("display","inline-block");
                $('.id_input_re_1').css("display","none");
                idckCheck = false;
            }
        }
    });
});

// 비밀번호 확인 일치 유효성 검사
$(".pwck_input").on("propertychange change keyup paste input", function (){

    var pw = $(".pw_input").val();
    var pwck = $(".pwck_input").val();
    $('.final_pwck_ck').css('display', 'none');

    if(pw == pwck){
        $('.pwck_input_re_1').css('display','block');
        $('.pwck_input_re_2').css('display','none');
        pwckcorCheck = true;
    }else{
        $('.pwck_input_re_1').css('display','none');
        $('.pwck_input_re_2').css('display','block');
        pwckcorCheck = false;
    }

});

// 이메일 형식 유효성 검사
function mailFormCheck(email){
    var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return form.test(email);
}

// 인증번호 이메일 전송
$(".mail_check_button").click(function (){

    // 입력한 이메일
    var email = $(".mail_input").val();
    // 인증번호 입력란
    var checkBox = $(".mail_check_input");
    // 인증번호 입력란 박스
    var boxWrap = $(".mail_check_input_box");
    // 이메일 입력 경고글
    var warnMsg = $(".mail_input_box_warn");

    // 이메일 형식 유효성 검사
    if(mailFormCheck(email)){
        warnMsg.html("이메일이 전송 되었습니다. 이메일을 확인해주세요.");
        warnMsg.css("display", "inline-block");
    }else{
        warnMsg.html("올바르지 않은 이메일 형식입니다.");
        warnMsg.css("display", "inline-block");
        return false;
    }

    $.ajax({
        type : "GET",
        url : "mailCheck?email=" + email,
        success : function (data){

            checkBox.attr("disabled",false);
            boxWrap.attr("id", "mail_check_input_box_true");
            code = data;

        }
    });
});

// 인증번호 비교
// blur는 포커스를 잃을 때 이벤트 발생
$(".mail_check_input").blur(function(){

    // 입력코드
    var inputCode = $(".mail_check_input").val();
    // 비교 결과
    var checkResult = $("#mail_check_input_box_warn");

    // 일치할 경우
    if(inputCode == code){
        checkResult.html("인증번호가 일치합니다.");
        checkResult.attr("class", "correct");
        mailnumCheck = true;

    // 일치하지 않을 경우
    } else {
        checkResult.html("인증번호를 다시 확인해주세요.");
        checkResult.attr("class", "incorrect");
        mailnumCheck = false;
    }

});

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

/* 캘린더 위젯 적용 */

// 설정
const config = {
    dateFormat: 'yy-mm-dd',
    showOn: "button",
    buttonText: "날짜 선택",
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    yearSuffix: '년',
    changeMonth: true,
    changeYear: true
}

// 캘린더
$(function () {
    $("input[name='memBirth']").datepicker(config);
});

</script>

</body>
</html>