package com.online.BookStore.dto;

import java.util.Objects;

public class OrderBookVo {

    private Integer bookId;
    private String bookTitle;
    private Integer Quantity;

    public OrderBookVo() {
    }

    public OrderBookVo(Integer bookId, String bookTitle, Integer quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        Quantity = quantity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBookVo that = (OrderBookVo) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(Quantity, that.Quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookTitle, Quantity);
    }
}
