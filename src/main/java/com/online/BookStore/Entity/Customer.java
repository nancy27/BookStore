package com.online.BookStore.Entity;

import javax.persistence.*;
import java.lang.reflect.Type;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_address")
    private String address;

    @Column(name="phone_no")
    private String phoneNo;

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String address, String phoneNo) {
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
