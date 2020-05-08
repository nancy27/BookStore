package com.online.BookStore.dto;

import com.online.BookStore.Entity.OrderBook;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderResponse {

    private Integer orderId;
    private String address;
    private String phoneNo;
    private Date createdDate;
    private Float finalPrice;
    private List<OrderBookVo> orderBookVoList;
    public OrderResponse() {
    }

    public OrderResponse(Integer orderId, String address, String phoneNo, Date createdDate,
                         Float finalPrice, List<OrderBookVo> orderBookVoList) {
        this.orderId = orderId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.createdDate = createdDate;
        this.finalPrice = finalPrice;
        this.orderBookVoList = orderBookVoList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<OrderBookVo> getOrderBookVoList() {
        return orderBookVoList;
    }

    public void setOrderBookVoList(List<OrderBookVo> orderBookVoList) {
        this.orderBookVoList = orderBookVoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponse that = (OrderResponse) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phoneNo, that.phoneNo) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(finalPrice, that.finalPrice) &&
                Objects.equals(orderBookVoList, that.orderBookVoList);

    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, address, phoneNo, createdDate, finalPrice, orderBookVoList);
    }
}
