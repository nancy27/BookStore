package com.online.BookStore.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="order_id")
    private Integer orderId;

    @Column(name="address")
    private String address;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="final_price")
    private Float finalPrice;

    @OneToMany(targetEntity = OrderBook.class, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private List<OrderBook> orderBookList;

    @Column(name="customer_id", insertable = false, updatable = false)
    private Integer customerId;

    @ManyToOne(targetEntity = Customer.class,cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id")
    private Customer customer;


    public Order() {
    }

    public Order(Integer orderId, String address, String phoneNo, Date createdDate, Float finalPrice, List<OrderBook> orderBookList
            , Customer customer) {
        this.orderId = orderId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.createdDate = createdDate;
        this.finalPrice = finalPrice;
        this.orderBookList = orderBookList;
        this.customer = customer;
    }
/*
    public Order(Integer orderId, String address, String phoneNo, Date createdDate, Float finalPrice, List<OrderBook> orderBookList) {
        this.orderId = orderId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.createdDate = createdDate;
        this.finalPrice = finalPrice;
        this.orderBookList = orderBookList;
    }
*/
    public Integer getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<OrderBook> getBookList() {
        return orderBookList;
    }

    public void setBookList(List<OrderBook> bookList) {
        this.orderBookList = bookList;
    }

    public List<OrderBook> getOrderBookList() {
        return orderBookList;
    }

    public void setOrderBookList(List<OrderBook> orderBookList) {
        this.orderBookList = orderBookList;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(address, order.address) &&
                Objects.equals(phoneNo, order.phoneNo) &&
                Objects.equals(createdDate, order.createdDate) &&
                Objects.equals(finalPrice, order.finalPrice) &&
                Objects.equals(orderBookList, order.orderBookList) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, address, phoneNo, createdDate, finalPrice, orderBookList, customerId, customer);
    }
}
