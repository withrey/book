package com.book.controller;

import com.book.model.*;
import com.book.service.AttachService;
import com.book.service.BookService;
import com.book.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private AttachService attachService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReplyService replyService;

    // 메인 페이지 이동
    @GetMapping("/main")
    public void mainPageGet(Model model) {

        model.addAttribute("cate1", bookService.getCateCode1());
        model.addAttribute("cate2", bookService.getCateCode2());

    }


    // 이미지 출력
    @GetMapping("/display")
    public ResponseEntity<byte[]> getImage(String fileName) {

        File file = new File("d:\\upload\\" + fileName);

        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders header = new HttpHeaders();
            header.add("Content-type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return result;

    }


    // 이미지 정보 반환
    @GetMapping("/getAttachList")
    public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId) {

        return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(bookId), HttpStatus.OK);

    }


    // 상품 검색
    @GetMapping("/search")
    public String searchGoodsGet(Criteria cri, Model model) {

        List<BookVO> list = bookService.getGoodsList(cri);

        if(!list.isEmpty()) {

            model.addAttribute("list", list);

        } else {

            model.addAttribute("listcheck", "empty");

            return "search";

        }

        model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));

        String[] typeArr = cri.getType().split("");

        for(String s : typeArr) {
            if(s.equals("T") || s.equals("A")) {
                model.addAttribute("filter_info", bookService.getCateInfoList(cri));
            }
        }

        return "search";

    }


    // 상품 상세
    @GetMapping("/goodsDetail/{bookId}")
    public String goodsDetailGet(@PathVariable("bookId")int bookId, Model model) {

        model.addAttribute("goodsInfo", bookService.getGoodsInfo(bookId));

        return "/goodsDetail";

    }


    // 리뷰 쓰기
    @GetMapping("/replyEnroll/{memId}")
    public String replyEnrollWindowGet(@PathVariable("memId")String memId, int bookId, Model model) {

        BookVO book = bookService.getBookIdName(bookId);
        model.addAttribute("bookInfo", book);
        model.addAttribute("memId", memId);

        return "/replyEnroll";

    }

    // 리뷰 수정 팝업창
    @GetMapping("/replyUpdate")
    public String replyUpdateWindowGet(ReplyDTO dto, Model model) {

        BookVO book = bookService.getBookIdName(dto.getBookId());
        model.addAttribute("bookInfo", book);
        model.addAttribute("replyInfo", replyService.getUpdateReply(dto.getReplyId()));
        model.addAttribute("memId", dto.getMemId());

        return "/replyUpdate";

    }

}
