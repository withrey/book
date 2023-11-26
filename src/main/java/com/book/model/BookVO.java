package com.book.model;

import java.util.Date;
import java.util.List;

public class BookVO {

    // 책 아이디
    private int bookId;

    // 책 제목
    private String bookName;

    // 작가 아이디
    private int authorId;

    // 작가 이름
    private String authorName;

    // 출판일
    private String bookDate;

    // 출판사
    private String bookPuble;

    // 카테고리 코드
    private String cateCode;

    // 카테고리 이름
    private String cateName;

    // 책 가격
    private int bookPrice;

    // 책 재고
    private int bookStock;

    // 책 할인율
    private double bookDiscount;

    // 책 정보
    private String bookInfo;

    // 등록 날짜
    private Date regDate;

    // 수정 날짜
    private Date updateDate;

    // 이미지 정보
    private List<AttachImageVO> imageList;



    private double ratingAvg;

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }



    public List<AttachImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<AttachImageVO> imageList) {
        this.imageList = imageList;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookPuble() {
        return bookPuble;
    }

    public void setBookPuble(String bookPuble) {
        this.bookPuble = bookPuble;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookStock() {
        return bookStock;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    public double getBookDiscount() {
        return bookDiscount;
    }

    public void setBookDiscount(double bookDiscount) {
        this.bookDiscount = bookDiscount;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", bookDate='" + bookDate + '\'' +
                ", bookPuble='" + bookPuble + '\'' +
                ", cateCode='" + cateCode + '\'' +
                ", cateName='" + cateName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookStock=" + bookStock +
                ", bookDiscount=" + bookDiscount +
                ", bookInfo='" + bookInfo + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", imageList=" + imageList +
                ", ratingAvg=" + ratingAvg +
                '}';
    }


    //    @Override
//    public String toString() {
//        return "BookVO{" +
//                "bookId=" + bookId +
//                ", bookName='" + bookName + '\'' +
//                ", authorId=" + authorId +
//                ", authorName='" + authorName + '\'' +
//                ", bookDate='" + bookDate + '\'' +
//                ", bookPuble='" + bookPuble + '\'' +
//                ", cateCode='" + cateCode + '\'' +
//                ", cateName='" + cateName + '\'' +
//                ", bookPrice=" + bookPrice +
//                ", bookStock=" + bookStock +
//                ", bookDiscount=" + bookDiscount +
//                ", bookInfo='" + bookInfo + '\'' +
//                ", regDate=" + regDate +
//                ", updateDate=" + updateDate +
//                ", imageList=" + imageList +
//                '}';
//    }
}
