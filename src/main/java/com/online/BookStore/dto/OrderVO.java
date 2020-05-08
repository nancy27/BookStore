package com.online.BookStore.dto;

import java.util.Date;

public class OrderVO {

    private Integer orderId;
    private String address;
    private String phoneNo;
    private Date createdDate;
    private Float finalPrice;


    public OrderVO() {
    }

    public OrderVO(Integer orderId, String address, String phoneNo, Date createdDate, Float finalPrice) {
        this.orderId = orderId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.createdDate = createdDate;
        this.finalPrice = finalPrice;
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
}
