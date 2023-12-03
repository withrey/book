package com.book.controller;

import com.book.model.MemberVO;
import com.book.model.OrderDTO;
import com.book.model.OrderPageDTO;
import com.book.service.MemberService;
import com.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/order/{memId}")
    public String orderPageGet(@PathVariable("memId") String memId, OrderPageDTO opd, Model model) {

        model.addAttribute("orderList", orderService.getGoodsInfo(opd.getOrders()));
        model.addAttribute("memberInfo", memberService.getMemberInfo(memId));

        return "/order";

    }


    @PostMapping("/order")
    public String orderPagePost(OrderDTO od, HttpServletRequest request, RedirectAttributes rttr) {

        System.out.println(od);

        orderService.order(od);

        MemberVO member = new MemberVO();
        member.setMemId(od.getMemId());

        HttpSession session = request.getSession();

        try {

            MemberVO memberLogin = memberService.memberLogin(member);
            memberLogin.setMemPw("");
            session.setAttribute("member", memberLogin);
            rttr.addFlashAttribute("msg", "order_OK");

        } catch (Exception e) {

            e.printStackTrace();

        }


        return "redirect:/main";

    }

}
