package com.book.controller;

import com.book.model.Criteria;
import com.book.model.ReplyDTO;
import com.book.model.ReplyPageDTO;
import com.book.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;


    // 댓글 등록
    @PostMapping("/enroll")
    public void enrollReplyPost(ReplyDTO dto) {

        replyService.enrollReply(dto);

    }


    // 댓글 체크
    // 댓글 있음 : 1  /  댓글 없음 : 0
    @PostMapping("/check")
    public String replyCheckPost(ReplyDTO dto) {

        return replyService.checkReply(dto);

    }


    // 댓글 페이징
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReplyPageDTO replyListPost(Criteria cri) {

        return replyService.replyList(cri);

    }


}
