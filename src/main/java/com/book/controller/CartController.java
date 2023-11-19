package com.book.controller;

import com.book.model.CartDTO;
import com.book.model.MemberVO;
import com.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 장바구니 추가 결과값 result
    // 0: 등록 실패
    // 1: 등록 성공
    // 2: 등록된 데이터 존재
    // 5: 로그인 필요


    // 장바구니 추가
    @PostMapping("/add")
    @ResponseBody
    public String addCartPost(CartDTO cart, HttpServletRequest request) {

        // 로그인 체크
        HttpSession session = request.getSession();
        MemberVO member = (MemberVO)session.getAttribute("member");
        if(member == null) {
            return "5";
        }

        // 카트 등록
        int result = cartService.addCart(cart);
        return result + "";

    }


    // 장바구니 정보 리스트
    @GetMapping("/{memId}")
    public String cartPageGet(@PathVariable("memId")String memId, Model model) {

        model.addAttribute("cartInfo", cartService.getCartList(memId));

        return "/cart";

    }


    // 장바구니 수량 수정
    @PostMapping("/update")
    public String updateCartPost(CartDTO cart) {

        cartService.modifyCount(cart);

        return "redirect:/cart/" + cart.getMemId();

    }


    // 장바구니 삭제
    @PostMapping("/delete")
    public String deleteCartPost(CartDTO cart) {

        cartService.deleteCart(cart.getCartId());

        return "redirect:/cart/" + cart.getMemId();

    }


}
