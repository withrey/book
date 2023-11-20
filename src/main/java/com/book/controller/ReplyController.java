package com.book.controller;

import com.book.model.ReplyDTO;
import com.book.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
