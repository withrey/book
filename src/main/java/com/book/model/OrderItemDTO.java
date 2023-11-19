package com.book.model;

public class OrderItemDTO {

    // 주문 번호
    private String OrderId;

    // 상품 번호
    private int bookId;

    // 주문 수량
    private int bookCount;

    // OrderItemDTO 기본키
    private int orderItemId;

    // 상품 한 개 가격
    private int bookPrice;

    // 상품 할인율
    private double bookDiscount;

    /* DB테이블에 존재하지 않는 데이터 */
    // 할인 적용된 가격
    private int salePrice;

    // 총 가격(할인 적용된 가격 * 주문 수량)
    private int totalPrice;


    public void initSaleTotal() {
        this.salePrice = (int)(this.bookPrice * (1 - this.bookDiscount));
        this.totalPrice = this.salePrice * this.bookCount;
    }


    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
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

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "OrderId='" + OrderId + '\'' +
                ", bookId=" + bookId +
                ", bookCount=" + bookCount +
                ", orderItemId=" + orderItemId +
                ", bookPrice=" + bookPrice +
                ", bookDiscount=" + bookDiscount +
                ", salePrice=" + salePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
