package com.online.BookStore.dto;

import java.util.Date;

public class BookDto {
    private Integer bookId;
    private String title;
    private String author;
    private Integer pages;
    private String description;
    private Date published;
    private double price;
    private Integer quantity;

    public BookDto() {
    }

    public BookDto(Integer bookId, String title, String author, Integer pages, String description, Date published, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.description = description;
        this.published = published;
        this.price = price;
    }

    public BookDto(Integer bookId, String title, String author, Integer pages, String description, Date published, double price, Integer quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.description = description;
        this.published = published;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
