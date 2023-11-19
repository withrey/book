<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="wrapper">
    <div class="wrap">
        <!-- gnv_area -->
        <div class="top_gnb_area">
            <ul class="list">
                <li><a href="<c:url value='/main'/>">메인 페이지</a></li>
                <li><a href="<c:url value='/member/logout'/>">로그아웃</a></li>
                <li>고객센터</li>
            </ul>
        </div>
        <!-- top_subject_area -->
        <div class="admin_top_wrap">
            <span>관리자 페이지</span>

        </div>
        <!-- contents-area -->
        <div class="admin_wrap">
            <!-- 네비영역 -->
            <div class="admin_navi_wrap">
                <ul>
                    <li >
                        <a class="admin_list_01" href="<c:url value='/admin/goodsEnroll'/>">상품 등록</a>
                    </li>
                    <li>
                        <a class="admin_list_02" href="<c:url value='/admin/goodsManage'/>">상품 목록</a>
                    </li>
                    <lI>
                        <a class="admin_list_03" href="<c:url value='/admin/authorEnroll'/>">작가 등록</a>
                    </lI>
                    <lI>
                        <a class="admin_list_04" href="<c:url value='/admin/authorManage'/>">작가 관리</a>
                    </lI>
                    <lI>
                        <a class="admin_list_05">회원 관리</a>
                    </lI>
                    <li>
                        <a class="admin_list_06" href="<c:url value='/admin/orderList'/>">주문 현황</a>
                    </li>
                </ul>

            </div>
