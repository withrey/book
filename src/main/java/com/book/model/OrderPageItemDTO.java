package com.book.model;

import java.util.List;

public class OrderPageItemDTO {

    // 뷰로부터 전달 받을 값
    private int bookId;
    private int bookCount;

    // DB에서 꺼내올 값
    private String bookName;
    private int bookPrice;
    private double bookDiscount;

    // 만들어 낼 값
    private int salePrice;
    private int totalPrice;


    // 상품 이미지
    private List<AttachImageVO> imageList;

    public void initSaleTotal() {
        this.salePrice = (int)(this.bookPrice * (1 - this.bookDiscount));
        this.totalPrice = this.salePrice * this.bookCount;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public double getBookDiscount() {
        return bookDiscount;
    }

    public void setBookDiscount(double bookDiscount) {
        this.bookDiscount = bookDiscount;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<AttachImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<AttachImageVO> imageList) {
        this.imageList = imageList;
    }


    @Override
    public String toString() {
        return "OrderPageItemDTO{" +
                "bookId=" + bookId +
                ", bookCount=" + bookCount +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookDiscount=" + bookDiscount +
                ", salePrice=" + salePrice +
                ", totalPrice=" + totalPrice +
                ", imageList=" + imageList +
                '}';
    }
}
