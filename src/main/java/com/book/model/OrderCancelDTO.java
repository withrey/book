package com.book.model;

public class OrderCancelDTO {

    // 회원 아이디
    private String memId;

    // 주문 아이디
    private String orderId;

    // 키워드
    private String keyword;

    // 페이지 표시 개수
    private int amount;

    // 현재 페이지 번호
    private int pageNum;


    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    @Override
    public String toString() {
        return "OrderCancelDTO{" +
                "memId='" + memId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", keyword='" + keyword + '\'' +
                ", amount=" + amount +
                ", pageNum=" + pageNum +
                '}';
    }
}
