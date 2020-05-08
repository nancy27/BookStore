package com.online.BookStore.Entity;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer bookId;

    @NotBlank(message = "Book title is mandatory")
    @Column(name="title" ,length=50, nullable=false, unique=true)
    private String title;

    @Column(name="author")
    @NotBlank(message = "Author name is mandatory")
    private String author;

    @Column(name="pages")
    @NotNull(message = "It should not be empty")
    //@Pattern(regexp = "^[1-9]+[0-9]*$", message="Enter valid no of pages")
    private Integer pages;

    @Column(name="description")
    @NotBlank
    @Size(min=3, max=200, message = "description size should be between {min} and {max}")
    private String description;

    @Column(name="published")
    @Past(message="Date cannot be in the future")
    private Date published;

    @Column(name="price")
    @NotNull(message = "Price details should be given correctly")
    private Double price;
    public Book() {
    }

    public Book(Integer bookId, @NotBlank(message = "Book title is mandatory") String title, @NotBlank(message = "Author name is mandatory") String author, @NotNull(message = "It should not be empty") Integer pages, @NotBlank @Size(min = 3, max = 200, message = "description size should be between {min} and {max}") String description, @Past(message = "Date cannot be in the future") Date published, @Size(min = 10, max = 3000, message = "Price details should be given correctly") Double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.description = description;
        this.published = published;
        this.price = price;
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

}
