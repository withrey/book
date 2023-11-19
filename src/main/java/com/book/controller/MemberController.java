package com.book.controller;

import com.book.model.MemberVO;
import com.book.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder pwEncoder;


    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinGet() {

//        System.out.println("회원가입 페이지 진입");

        return "member/join";

    }

    // 회원가입
    @PostMapping("/join")
    public String joinPost(MemberVO member, RedirectAttributes rttr) throws Exception {

        // 인코딩 전 비밀번호
        String rawPw = "";
        // 인코딩 후 비밀번호
        String encodePw = "";

        // 비밀번호 데이터 얻음
        rawPw = member.getMemPw();
        // 비밀번호 인코딩
        encodePw = pwEncoder.encode(rawPw);
        // 인코딩된 비밀번호 member객체에 다시 저장
        member.setMemPw(encodePw);

        // 회원가입 쿼리 실행
        int result = memberService.memberJoin(member);

        // 회원가입 성공 전달
        rttr.addFlashAttribute("join_result",result);

        return "redirect:/main";

    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginGet() {

//        System.out.println("로그인 페이지 진입");

        return "member/login";

    }

    // 로그인
    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {

//        System.out.println("login 메서드 진입");
//        System.out.println("전달된 데이터 : " + member);

        // 세션
        HttpSession session = request.getSession();
        // 인코딩 전 비밀번호
        String rawPw = "";
        // 인코딩 후 비밀번호
        String encodePw = "";

        MemberVO lvo = memberService.memberLogin(member);

        // 로그인 성공시
        if(lvo != null) {

            // 사용자가 제출할 비밀번호
            rawPw = member.getMemPw();
            // DB에 저장한 인코딩된 비밀번호
            encodePw = lvo.getMemPw();

            // 비밀번호가 일치할 시
            if(true == pwEncoder.matches(rawPw, encodePw)) {

                // 인코딩된 비밀번호 정보 지움
                lvo.setMemPw("");
                // session에 사용자 정보 저장
                session.setAttribute("member", lvo);
                return "redirect:/main";

            // 비밀번호가 일치하지 않을 시
            }else{

                rttr.addFlashAttribute("result", 0);
                return "redirect:/member/login";

            }

        // 로그인 실패시
        }else{

            rttr.addFlashAttribute("result", 0);
            return "redirect:/member/login";

        }
    }

    @GetMapping("/logout")
    public String logoutMainGet(HttpServletRequest request) throws Exception {

        System.out.println("logoutMainGet메서드 진입");
        
        HttpSession session = request.getSession();

        // 세션 삭제
        session.invalidate();

        return "redirect:/main";

    }

    // 뷰에서 ajax를 통해서 서버에 요청시 @ResponseBody를 붙여야함
    @PostMapping("/logout")
    @ResponseBody
    public void logoutPost(HttpServletRequest request) throws Exception {

        System.out.println("비동기 로그아웃 메서드 진입");

        HttpSession session = request.getSession();

        session.invalidate();

    }

    // 아이디 중복 체크
    @PostMapping("/memberIdChk")
    @ResponseBody
    public String memberIdChkPost(String memId) throws Exception {

        int result = memberService.idCheck(memId);

        if(result !=0 ){

            // result == 1
            // 중복 아이디가 존재함
            return "fail";

        } else {

            // result == 0
            // 중복 아이디가 존재하지 않음
            return "success";

        }
    }

    // 메일 인증번호 확인
    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheckGet(String email) throws Exception {

        // view 로부터 넘어온 데이터 확인
        System.out.println("이메일 데이터 전송 확인");
        System.out.println("인증번호 : " + email);

        // 인증번호(난수) 생성
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);

        // 이메일 보내기
        String setForm = "sonno1001@naver.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" +
                "인증 번호는 " + checkNum + "입니다." +
                "<br>" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setForm);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String num = Integer.toString(checkNum);

        return num;

    }


}
