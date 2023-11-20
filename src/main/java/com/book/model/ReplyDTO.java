package com.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReplyDTO {

    private int replyId;

    private int bookId;

    private String memId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regDate;

    // 댓글 내용
    private String content;

    // 별점
    private double rating;


    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "ReplyDTO{" +
                "replyId=" + replyId +
                ", bookId=" + bookId +
                ", memId='" + memId + '\'' +
                ", regDate=" + regDate +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
