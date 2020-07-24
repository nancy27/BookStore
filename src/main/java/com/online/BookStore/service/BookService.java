package com.online.BookStore.service;

import com.online.BookStore.Entity.Book;
import com.online.BookStore.dto.BookDto;
import com.online.BookStore.exception.DataNotFoundException;
import com.online.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String addBook(Book book) throws Exception {
       String title= book.getTitle();
       Optional<Book> retrievedBook = bookRepository.findByTitle(title);
       if(retrievedBook.isPresent()){
           throw new Exception("Book is already present with the same title");
       }
       bookRepository.save(book);
       return "Saved Successfully";
   }

    public BookDto getBookDetails(Integer bookId)  {

       Optional<Book> book = bookRepository.findByBookId(bookId);
       if(!book.isPresent()){
           throw new DataNotFoundException("Book is not present ");
       }
       Book newBook= book.get();
       return new BookDto(newBook.getBookId(),newBook.getTitle(),newBook.getAuthor(),
               newBook.getPages(),newBook.getDescription(),newBook.getPublished(),newBook.getPrice());
    }
/*
    public String addManyBooks(List<Book> bookList) throws Exception {
        List<String> title= new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            title.add(i, bookList.get(i).getTitle());
        }
        String bookTitle ="";
        Optional<List<Book>> retrievedBookOptional = bookRepository.findAllByTitle(title);
        if(retrievedBookOptional.isPresent()){
            List<Book> bookLis = retrievedBookOptional.get();
            for (int i = 0; i < bookLis.size() ; i++) {
               bookTitle= bookTitle+","+ bookLis.get(i).getTitle();
            }
            throw new Exception("Book "+ bookTitle +" is already present with the same title");
        }

      try{
       List<Book> bookList1=bookRepository.saveAll(bookList);
      }
      catch(ConstraintViolationException e){
          throw new ConstraintViolationException((Set<? extends ConstraintViolation<?>>) e);
      }
       return "Saved all books!";
    }
*/
    public List<BookDto> getListOfBooks() {

       Optional<List<Book>> bookListOptional= Optional.ofNullable(bookRepository.findAll());
       if((!bookListOptional.isPresent()) || (bookListOptional == null)){
           throw new DataNotFoundException("Books cannot be found");
       }
          List<Book> bookList= bookListOptional.get();
       List<BookDto> bookDtoList=new ArrayList<>();
        for (int i = 0; i <bookList.size() ; i++) {
            Book newBook= bookList.get(i);
            BookDto bookDto= new BookDto(newBook.getBookId(),newBook.getTitle(),newBook.getAuthor(),
                    newBook.getPages(),newBook.getDescription(),newBook.getPublished(),newBook.getPrice());
            bookDtoList.add(bookDto);
        }
       return bookDtoList;
    }

    public String deleteABook(Integer bookId) {
        Optional<Book> optionalBook = bookRepository.findByBookId(bookId);
        if(!optionalBook.isPresent()){
            throw new DataNotFoundException("Book is not present ");
        }
       bookRepository.delete(optionalBook.get());
       return "Deleted Successfully!";
    }

    public BookDto updateBookDetails(Book book,Integer bookId) {
        Optional<Book> optionalBook = bookRepository.findByBookId(bookId);
        if(!optionalBook.isPresent()){
            throw new DataNotFoundException("Book is not present ");
        }
        Book oldBook= optionalBook.get();
        oldBook.setBookId(book.getBookId());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setDescription(book.getDescription());
        oldBook.setPages(book.getPages());
        oldBook.setPublished(book.getPublished());
        oldBook.setTitle(book.getTitle());
        oldBook.setPrice(book.getPrice());
        Book updatedBook= bookRepository.save(oldBook);
        return new BookDto(updatedBook.getBookId(),updatedBook.getTitle(),
                updatedBook.getAuthor(),updatedBook.getPages(),updatedBook.getDescription(),updatedBook.getPublished(),updatedBook.getPrice());
    }
}
