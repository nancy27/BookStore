package com.online.BookStore.controller;

import com.online.BookStore.Entity.Book;
import com.online.BookStore.dto.BookDto;
import com.online.BookStore.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore/book")
@Validated
public class bookController {
    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST,value="/addbook")
    public String addBook(@Valid @RequestBody Book book) throws Exception {
        return bookService.addBook(book);
    }
/*
    @RequestMapping(method = RequestMethod.POST, value="/addManyBooks")
    public String addManyBooks(@Valid @RequestBody List<Book> bookList) throws Exception {
        return bookService.addManyBooks(bookList);
    }
*/
    @RequestMapping("/{bookId}")
    public BookDto getBookDetails(@PathVariable Integer bookId) throws NotFoundException
    {
         return bookService.getBookDetails(bookId);
    }

    @RequestMapping("/getAllBooks")
    public List<BookDto> getListOfBooks(){
        return bookService.getListOfBooks();
    }

    @RequestMapping(method = RequestMethod.PUT ,value="/updatebook/{bookId}")
    public BookDto updateBookDetail(@Valid @RequestBody Book book, @PathVariable Integer bookId){
        return bookService.updateBookDetails(book,bookId);

    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteBook/{bookId}")
    public String deleteABook(@PathVariable Integer bookId ){
        return bookService.deleteABook(bookId);
    }
}
