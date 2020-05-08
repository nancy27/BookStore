package com.online.BookStore.Entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="OrderBook")
public class OrderBook {

    @Id
    @Column(name="ordering_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer orderingId;

    @Column(name="book_id")
    private Integer bookId;

    @Column(name="order_id")
    private Integer orderId;

    @Column(name="quantity")
    private Integer quantity;

    public OrderBook() {
    }

    public OrderBook(Integer orderingId, Integer bookId, Integer orderId,Integer quantity) {
        this.orderingId = orderingId;
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(Integer orderingId) {
        this.orderingId = orderingId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBook orderBook = (OrderBook) o;
        return Objects.equals(orderingId, orderBook.orderingId) &&
                Objects.equals(bookId, orderBook.bookId) &&
                Objects.equals(orderId, orderBook.orderId) &&
                Objects.equals(quantity, orderBook.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderingId, bookId, orderId, quantity);
    }
}



