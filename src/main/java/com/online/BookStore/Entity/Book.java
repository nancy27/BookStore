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
    @Column(name="title" ,length=50, nullable=false, unique=false)
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


    public Book() {
    }

    public Book(Integer bookId, String title, String author, Integer pages, String description, Date published) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.description = description;
        this.published = published;
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
}
