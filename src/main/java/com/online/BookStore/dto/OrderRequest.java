package com.online.BookStore.dto;

import java.util.Date;
import java.util.List;

public class OrderRequest {
    private Integer orderId;
    private String address;
    private String phoneNo;
    private Date createdDate;
    private List<BookDto> bookDtoList;
    private Integer customerId;

    public OrderRequest() {
    }

    public OrderRequest(Integer orderId, String address, String phoneNo, Date createdDate, List<BookDto> bookDtoList, Integer customerId) {
        this.orderId = orderId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.createdDate = createdDate;
        this.bookDtoList = bookDtoList;
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
