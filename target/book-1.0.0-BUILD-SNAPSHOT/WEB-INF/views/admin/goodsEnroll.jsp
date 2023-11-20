<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="<c:url value='/css/admin/goodsEnroll.css'/>">

    <%-- datepicker 위젯 1 --%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>

    <%-- 위지윅 에디터 CDN --%>
    <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>

    <%-- datepicker 위젯 2 --%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

    <style type="text/css">
        #result_card img{
            max-width: 100%;
            height: auto;
            display: block;
            padding: 5px;
            margin-top: 10px;
            margin: auto;
        }
        #result_card {
            position: relative;
        }
        .imgDeleteBtn{
            position: absolute;
            top: 0;
            right: 5%;
            background-color: #ef7d7d;
            color: wheat;
            font-weight: 900;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            line-height: 26px;
            text-align: center;
            border: none;
            display: block;
            cursor: pointer;
        }

    </style>

</head>
<body>

<%@include file="../includes/admin/header.jsp" %>

<div class="admin_content_wrap">
    <div class="admin_content_subject"><span>상품 등록</span></div>
    <div class="admin_content_main">
        <form action="<c:url value='/admin/goodsEnroll'/>" method="post" id="enrollForm">

            <div class="form_section">
                <div class="form_section_title">
                    <label>책 제목</label>
                </div>
                <div class="form_section_content">
                    <input name="bookName">
                    <span class="ck_warn bookName_warn">책 이름을 입력해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>작가</label>
                </div>
                <div class="form_section_content">
                    <input id="authorName_input" readonly="readonly">
                    <input id="authorId_input" name="authorId" type="hidden">
                    <button class="authorId_btn">작가 선택</button>
                    <span class="ck_warn authorId_warn">작가를 선택해주세요</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>출판일</label>
                </div>
                <div class="form_section_content">
                    <input name="bookDate" autocomplete="off" readonly="readonly">
                    <span class="ck_warn bookDate_warn">출판일을 선택해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>출판사</label>
                </div>
                <div class="form_section_content">
                    <input name="bookPuble">
                    <span class="ck_warn bookPuble_warn">출판사를 입력해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>책 카테고리</label>
                </div>
                <div class="form_section_content">
                    <div class="cate_wrap">
                        <span>대분류</span>
                        <select class="cate1">
                            <option selected value="none">선택</option>
                        </select>
                    </div>
                    <div class="cate_wrap">
                        <span>중분류</span>
                        <select class="cate2">
                            <option selected value="none">선택</option>
                        </select>
                    </div>
                    <div class="cate_wrap">
                        <span>소분류</span>
                        <select class="cate3" name="cateCode">
                            <option selected value="none">선택</option>
                        </select>
                    </div>
                    <span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 가격</label>
                </div>
                <div class="form_section_content">
                    <input name="bookPrice" value="0">
                    <span class="ck_warn bookPrice_warn">상품 가격을 입력해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 재고</label>
                </div>
                <div class="form_section_content">
                    <input name="bookStock" value="0">
                    <span class="ck_warn bookStock_warn">상품 재고를 입력해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 할인율</label>
                </div>
                <div class="form_section_content">
                    <input id="discount_interface" maxlength="2" value="0">
                    <input name="bookDiscount" type="hidden" value="0">
                    <span class="step_val">할인 가격 : <span class="span_discount"></span></span>
                    <span class="ck_warn bookDiscount_warn">1~99 숫자를 입력해주세요.</span>
                </div>
            </div>
            <div class="form_section">
                <div class="form_section_title">
                    <label>책 정보</label>
                </div>
                <div class="form_section_content bif">
                    <textarea name="bookInfo" id="bookInfo_textarea"></textarea>
                    <span class="ck_warn bookInfo_warn">책 정보를 입력해주세요.</span>
                </div>
            </div>

            <div class="form_section">
                <div class="form_section_title">
                    <label>상품 이미지</label>
                </div>
                <div class="form_section_content">
                    <input type="file" id ="fileItem" name='uploadFile' style="height: 30px;">
                    <div id="uploadResult">
                        <!--
                        <div id="result_card">
                            <div class="imgDeleteBtn">x</div>
                            <img src="<c:url value='/display?fileName=test.jpg'/>">
                        </div>
                        -->
                    </div>
                </div>
            </div>

        </form>
        <div class="btn_section">
            <button id="cancelBtn" class="btn">취 소</button>
            <button id="enrollBtn" class="btn enroll_btn">등 록</button>
        </div>
    </div>
</div>

<%@include file="../includes/admin/footer.jsp" %>


<script>

let enrollForm = $("#enrollForm");

// 취소 버튼
$("#cancelBtn").click(function (){

    location.href="<c:url value='/admin/goodsManage'/>";

});

// 상품 등록 버튼
$("#enrollBtn").click(function (e){

    e.preventDefault();

    // 체크 변수
    let bookNameCk = false;
    let authorIdCk = false;
    let bookDateCk = false;
    let bookPubleCk = false;
    let cateCodeCk = false;
    let bookPriceCk = false;
    let bookStockCk = false;
    let discountCk = false;
    let bookInfoCk = false;

    // 체크 대상 변수
    let bookName = $("input[name='bookName']").val();
    let authorId = $("input[name='authorId']").val();
    let bookDate = $("input[name='bookDate']").val();
    let bookPuble = $("input[name='bookPuble']").val();
    // div 안에 select로 선택을 하므로 $("select[name='cateCode']") 사용
    let cateCode = $("select[name='cateCode']").val();
    let bookPrice = $("input[name='bookPrice']").val();
    let bookStock = $("input[name='bookStock']").val();
    let bookDiscount = $("#discount_interface").val();
    let bookInfo = $(".bif p").html();

    // 공란 체크
    // 공란일 경우 span 태그를 보여주고 체크 변수에 false 값을 대입
    // 공란이 아닐 경우 span 태그를 숨기고 체크 변수에 true 값을 대입
    if(bookName) {
        $(".bookName_warn").css('display','none');
        bookNameCk = true;
    } else {
        $(".bookName_warn").css('display','block');
        bookNameCk = false;
    }

    if(authorId) {
        $(".authorId_warn").css('display','none');
        authorIdCk = true;
    } else {
        $(".authorId_warn").css('display','block');
        authorIdCk = false;
    }

    if(bookDate) {
        $(".bookDate_warn").css('display','none');
        bookDateCk = true;
    } else {
        $(".bookDate_warn").css('display','block');
        bookDateCk = false;
    }

    if(bookPuble) {
        $(".bookPuble_warn").css('display','none');
        bookPubleCk = true;
    } else {
        $(".bookPuble_warn").css('display','block');
        bookPubleCk = false;
    }

    if(cateCode != 'none'){
        $(".cateCode_warn").css('display','none');
        cateCodeCk = true;
    } else {
        $(".cateCode_warn").css('display','block');
        cateCodeCk = false;
    }

    if(bookPrice != 0) {
        $(".bookPrice_warn").css('display','none');
        bookPriceCk = true;
    } else {
        $(".bookPrice_warn").css('display','block');
        bookPriceCk = false;
    }

    if(bookStock != 0) {
        $(".bookStock_warn").css('display','none');
        bookStockCk = true;
    } else {
        $(".bookStock_warn").css('display','block');
        bookStockCk = false;
    }

    // 입력값이 문자인지 숫자인지 판단하기 위해서 isNan() 메서드 사용
    if(!isNaN(bookDiscount)){
        $(".bookDiscount_warn").css('display','none');
        discountCk = true;
    } else {
        $(".bookDiscount_warn").css('display','block');
        discountCk = false;
    }

    if(bookInfo != '<br data-cke-filler="true">') {
        $(".bookInfo_warn").css('display','none');
        bookInfoCk = true;
    } else {
        $(".bookInfo_warn").css('display','block');
        bookInfoCk = false;
    }


    if(bookNameCk && authorIdCk && bookDateCk && bookPubleCk && cateCodeCk && bookPriceCk && bookStockCk && discountCk && bookInfoCk) {
        enrollForm.submit();
    } else {
        return false;
    }

});


// 이미지 업로드
$("input[type='file']").on("change", function(e){

    // 이미지 존재시 삭제
    if($("#result_card").length > 0) {
        deleteFile();
    }

    let formData = new FormData();
    let fileInput = $('input[name="uploadFile"]');
    let fileList = fileInput[0].files;
    let fileObj = fileList[0];

    // 이미지 파일 체크
    if(!fileCheck(fileObj.name, fileObj.size)) {
        return false;
    }

    formData.append("uploadFile", fileObj);

    $.ajax({
        type : 'POST',
        url : '<c:url value="/admin/uploadAjaxAction"/>',
        data : formData,
        dataType : 'json',
        processData : false,
        contentType : false,
        success : function (result) {
            console.log(result);
            showUploadImage(result);
        },
        error : function (result) {
            alert("이미지 파일이 아닙니다.");
        }
    });

});

// var, method related with attachFile
let regex = new RegExp("(.*?)\.(jpg|png)$");
let maxSize = 1048576;

function fileCheck(fileName, fileSize) {

    if(fileSize >= maxSize) {
        alert("파일 사이즈가 초과되어서 업로드할 수 없습니다.");
        return false;
    }

    if(!regex.test(fileName)) {
        alert("해당 종류의 파일은 업로드할 수 없습니다.");
        return false;
    }

    return true;

}

// 이미지 출력
function showUploadImage(uploadResultArr) {

    // 전달받은 데이터 검증
    if(!uploadResultArr || uploadResultArr.length == 0) {return}

    let uploadResult = $("#uploadResult");

    let obj = uploadResultArr[0];

    let str = "";

    let fileCallPath = encodeURIComponent(obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName);

    str += "<div id='result_card'>";
    str += "<img src='<c:url value="/display?fileName="/>" + fileCallPath + "'>";
    // 삭제 버튼
    str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
    str += "<input type='hidden' name='imageList[0].fileName' value='" + obj.fileName + "'>";
    str += "<input type='hidden' name='imageList[0].uuid' value='" + obj.uuid + "'>";
    str += "<input type='hidden' name='imageList[0].uploadPath' value='" + obj.uploadPath + "'>";
    str += "</div>";

    uploadResult.append(str);

}

// 이미지 삭제 버튼 동작
$("#uploadResult").on("click", ".imgDeleteBtn", function (e){

    deleteFile();

});

// 파일 삭제 메서드
function deleteFile() {

    let targetFile = $(".imgDeleteBtn").data("file");

    let targetDiv = $("#result_card");

    $.ajax({
        url: '<c:url value="/admin/deleteFile"/>',
        data: {fileName : targetFile},
        dataType: 'text',
        type: 'POST',
        success: function(result) {
            console.log(result);

            targetDiv.remove();
            $("input[type='file']").val("");
        },
        error: function(result) {
            console.log(result);

            alert("파일을 삭제하지 못했습니다.")
        }
    });
}


// 위지윅 적용
// 책 정보
ClassicEditor
    .create(document.querySelector('#bookInfo_textarea'))
    .catch(error=>{
        console.error(error);
    });

// 캘린더 설정
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

// 캘린더 위젯 적용
// 캘린더
$(function (){
    $("input[name='bookDate']").datepicker(config);
});

// 작가 선택 버튼
$(".authorId_btn").on("click", function (e){

    e.preventDefault();

    let popUrl = "<c:url value='/admin/authorPop'/>";
    let popOption = "width=650px, height=550px, top=300px, left=300px, scrollbars=yes";

    window.open(popUrl, "작가 찾기", popOption);

});


// 카테고리
let cateList = JSON.parse('${cateList}');

let cate1Array = new Array();
let cate2Array = new Array();
let cate3Array = new Array();

let cate1Obj = new Object();
let cate2Obj = new Object();
let cate3Obj = new Object();

let cateSelect1 = $(".cate1");
let cateSelect2 = $(".cate2");
let cateSelect3 = $(".cate3");


// 카테고리 배열 초기화 메서드
function makeCateArray(obj, array, cateList, tier) {
    for (let i = 0; i < cateList.length; i++) {
        if (cateList[i].tier === tier) {
            obj = new Object();

            obj.cateName = cateList[i].cateName;
            obj.cateCode = cateList[i].cateCode;
            obj.cateParent = cateList[i].cateParent;

            array.push(obj);
        }
    }
}


// 배열 초기화
makeCateArray(cate1Obj, cate1Array, cateList, 1);
makeCateArray(cate2Obj, cate2Array, cateList, 2);
makeCateArray(cate3Obj, cate3Array, cateList, 3);

// 대분류 <option> 태그
for (let i = 0; i < cate1Array.length; i++) {

    cateSelect1.append("<option value='" + cate1Array[i].cateCode + "'>" + cate1Array[i].cateName + "</option>");

}

// 중분류 <option> 태그
$(cateSelect1).on("change", function () {

    let selectVal1 = $(this).find("option:selected").val();

    cateSelect2.children().remove();
    // 대분류 변경시 소분류 초기화
    cateSelect3.children().remove();

    cateSelect2.append("<option value='none'>선택</option>");
    // 대분류 변경시 소분류 초기화
    cateSelect3.append("<option value='none'>선택</option>");

    for (let i = 0; i < cate2Array.length; i++) {
        if (selectVal1 === cate2Array[i].cateParent) {
            cateSelect2.append("<option value='" + cate2Array[i].cateCode + "'>" + cate2Array[i].cateName + "</option>");
        }
    }
});

// 소분류 <option> 태그
$(cateSelect2).on("change", function () {

    let selectVal2 = $(this).find("option:selected").val();

    cateSelect3.children().remove();

    cateSelect3.append("<option value='none'>선택</option>");

    for (let i = 0; i < cate3Array.length; i++) {
        if (selectVal2 === cate3Array[i].cateParent) {
            cateSelect3.append("<option value='" + cate3Array[i].cateCode + "'>" + cate3Array[i].cateName + "</option>");
        }
    }
});

// 할인율 Input 설정
$("#discount_interface").on("propertychange change keyup paste input", function (){

    let userInput = $("#discount_interface");
    let discountInput = $("input[name='bookDiscount']");

    // 사용자가 입력할 할인값
    let discountRate = userInput.val();
    // 서버에 전송할 할인값
    let sendDiscountRate = discountRate / 100;
    // 원가
    let goodsPrice = $("input[name='bookPrice']").val();
    // 할인가격
    let discountPrice = goodsPrice * (1 - sendDiscountRate);

    if(!isNaN(discountRate)){
        $(".span_discount").html(discountPrice);
        discountInput.val(sendDiscountRate);
    }

});

$("input[name='bookPrice']").on("change", function (){

    let userInput = $("#discount_interface");
    let discountInput = $("input[name='bookDiscount']");

    // 사용자가 입력할 할인값
    let discountRate = userInput.val();
    // 서버에 전송할 할인값
    let sendDiscountRate = discountRate / 100;
    // 원가
    let goodsPrice = $("input[name='bookPrice']").val();
    // 할인가격
    let discountPrice = goodsPrice * (1 - sendDiscountRate);

    if(!isNaN(discountRate)){
        $(".span_discount").html(discountPrice);
    }

});

</script>

</body>
</html>