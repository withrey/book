package com.book.model;

import lombok.Getter;

import java.util.List;

public class CartDTO {

    @Getter
    private int cartId;

    @Getter
    private String memId;

    @Getter
    private int bookId;

    @Getter
    private int bookCount;


    @Getter
    private String bookName;

    @Getter
    private int bookPrice;

    @Getter
    private double bookDiscount;


    private int salePrice;

    private int totalPrice;

    // 상품 이미지
    private List<AttachImageVO> imageList;


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<AttachImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<AttachImageVO> imageList) {
        this.imageList = imageList;
    }

    public void initSaleTotal() {
        this.salePrice = (int)(this.bookPrice * (1 - this.bookDiscount));
        this.totalPrice = this.salePrice * this.bookCount;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + cartId +
                ", memId='" + memId + '\'' +
                ", bookId=" + bookId +
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
