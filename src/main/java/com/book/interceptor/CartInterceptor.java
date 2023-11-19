package com.book.interceptor;

import com.book.model.MemberVO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        MemberVO member = (MemberVO)session.getAttribute("member");

        // 로그인이 되어있지 않을 경우
        if(member == null) {
            response.sendRedirect("/main");
            return false;

        // 로그인이 되어있는 경우
        } else {

            return true;

        }
    }

}
