package com.book.model;

import java.sql.Date;
import java.util.List;

public class OrderDTO {

    // 주문 번호
    private String orderId;

    // 배송 받는 사람
    private String addressee;

    // 회원 아이디
    private String memId;

    // 우편번호
    private String memAddr1;

    // 회원 주소
    private String memAddr2;

    // 회원 상세주소
    private String memAddr3;

    // 주문 상태
    private String orderState;

    // 주문 상품
    private List<OrderItemDTO> orders;

    // 배송비
    private int deliveryCost;

    // 주문 날짜
    private Date orderDate;


    /* DB테이블에 존재하지 않는 데이터 */
    // 판매가(모든 상품 비용)
    private int orderSalePrice;

    // 최종 판매 비용
    private int orderFinalSalePrice;


    public void getOrderPriceInfo() {
        // 상품 비용
        for(OrderItemDTO order : orders) {
            orderSalePrice += order.getTotalPrice();
        }

        // 배송 비용
        if(orderSalePrice >= 30000) {
            deliveryCost = 0;
        } else {
            deliveryCost = 3000;
        }

        // 최종 비용(상품비용 + 배송비)
        orderFinalSalePrice = orderSalePrice + deliveryCost;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemAddr1() {
        return memAddr1;
    }

    public void setMemAddr1(String memAddr1) {
        this.memAddr1 = memAddr1;
    }

    public String getMemAddr2() {
        return memAddr2;
    }

    public void setMemAddr2(String memAddr2) {
        this.memAddr2 = memAddr2;
    }

    public String getMemAddr3() {
        return memAddr3;
    }

    public void setMemAddr3(String memAddr3) {
        this.memAddr3 = memAddr3;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public List<OrderItemDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDTO> orders) {
        this.orders = orders;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderSalePrice() {
        return orderSalePrice;
    }

    public void setOrderSalePrice(int orderSalePrice) {
        this.orderSalePrice = orderSalePrice;
    }

    public int getOrderFinalSalePrice() {
        return orderFinalSalePrice;
    }

    public void setOrderFinalSalePrice(int orderFinalSalePrice) {
        this.orderFinalSalePrice = orderFinalSalePrice;
    }


    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", addressee='" + addressee + '\'' +
                ", memId='" + memId + '\'' +
                ", memAddr1='" + memAddr1 + '\'' +
                ", memAddr2='" + memAddr2 + '\'' +
                ", memAddr3='" + memAddr3 + '\'' +
                ", orderState='" + orderState + '\'' +
                ", orders=" + orders +
                ", deliveryCost=" + deliveryCost +
                ", orderDate=" + orderDate +
                ", orderSalePrice=" + orderSalePrice +
                ", orderFinalSalePrice=" + orderFinalSalePrice +
                '}';
    }
}
