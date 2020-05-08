package com.online.BookStore.dto;

public class CustomerVo {
    private Integer customerId;
    private String customerName;
    private String address;
    private String phoneNo;

    public CustomerVo() {
    }

    public CustomerVo(Integer customerId, String customerName, String address, String phoneNo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
}
