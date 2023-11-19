package com.book.interceptor;

import com.book.model.MemberVO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        MemberVO lvo = (MemberVO)session.getAttribute("member");

        // 관리자 계정이 아닐 경우
        if(lvo == null || lvo.getAdminCk() == 0){

            // 메인페이지로 리다이렉트
            response.sendRedirect("redirect:/main");

            return false;
        }

        // 관리자 계정인 경우
        // (lvo != null || lvo.getAdminCk() == 1)
        return true;

    }

}
