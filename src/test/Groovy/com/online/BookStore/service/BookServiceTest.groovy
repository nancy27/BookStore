package com.online.BookStore.service

import com.online.BookStore.Entity.Book
import com.online.BookStore.dto.BookDto
import com.online.BookStore.exception.DataNotFoundException
import com.online.BookStore.repository.BookRepository
import spock.lang.Specification

class BookServiceTest extends Specification {

    BookRepository bookRepository = Mock(BookRepository)
    BookService bookService = new BookService(bookRepository)


    def "testing add book when book in valid"() {
        given:

        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        bookRepository.findByTitle(_) >> Optional.empty()
        bookRepository.save(_)
        when:
        def result = bookService.addBook(book)
        then:
        result == "Saved Successfully"
    }

    def "testing add book when book is invalid"() {
        given:
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        bookRepository.findByTitle(_) >> Optional.of(book)
        when:
        bookService.addBook(book)
        then:
        thrown(Exception)
    }

    def "testing getBookDetails when book id is found in db"() {
        given:
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        Integer bookId = 1
        BookDto bookDto = new BookDto(book.getBookId(), book.getTitle(), book.getAuthor(),
                book.getPages(), book.getDescription(), book.getPublished(), book.getPrice())
        bookRepository.findByBookId(_) >>Optional.of(book)
        when:
        def result = bookService.getBookDetails(bookId)
        then:
        result.equals(bookDto) == true
    }

    def "testing getBookDetails when book id is notfound in db"() {
        given:
        Integer bookId = 1
        bookRepository.findByBookId(_) >> Optional.empty()
        when:
        bookService.getBookDetails(bookId)
        then:
        thrown(DataNotFoundException)
    }
    def "testing getBookDetails when book id is null"() {
        given:
        Integer bookId = null
        bookRepository.findByBookId(_) >> Optional.empty()
        when:
        bookService.getBookDetails(bookId)
        then:
        thrown(DataNotFoundException)
    }

    def "testing add many books when the list is valid "(){
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        List<Book> bookList= new ArrayList<>(Arrays.asList(book))
        List<String> title=new ArrayList<>(Arrays.asList(book.title))
        bookRepository.findAllByTitle(_)>>Optional.empty()
        bookRepository.saveAll(_) >> bookList
        when:
        def result = bookService.addManyBooks(bookList)
        then:
        result == "Saved all books!"
    }

    def "testing add many books when some are already available "(){
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        List<Book> bookList= new ArrayList<>(Arrays.asList(book))
        bookRepository.findAllByTitle(_)>> Optional.of(bookList)
        when:
        bookService.addManyBooks(bookList)
        then:
        thrown(Exception)
    }

    def"testing get list of Books from db when all are retrieved"(){
        given:
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        List<Book> bookList= new ArrayList<>(Arrays.asList(book))
        BookDto bookDto = new BookDto(book.getBookId(), book.getTitle(), book.getAuthor(),
                book.getPages(), book.getDescription(), book.getPublished(), book.getPrice())
        List<BookDto> bookDtoList= new ArrayList<>(Arrays.asList(bookDto))
        bookRepository.findAll()>>bookList
        when:
        def result= bookService.getListOfBooks()
        then:
        result.equals(bookDtoList)==true
    }

    def "testing get list of Books from db when books aren't retrieved"(){

        given:
        bookRepository.findAll()
        when:
        bookService.getListOfBooks()
        then:
        thrown(DataNotFoundException)

    }

    def "testing delete book when bookid is valid  to delete"(){
        given:
        Integer bookId=1
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        bookRepository.findByBookId(bookId) >> Optional.of(book)
        when:
        def result=bookService.deleteABook(bookId)
        then:
        result=="Deleted Successfully!"
    }

    def "testing delete book when bookid is invalid  to delete"(){
        given:
        Integer bookId=1
        bookRepository.findByBookId(bookId) >> Optional.empty()
        when:
        def result=bookService.deleteABook(bookId)
        then:
        thrown(DataNotFoundException)
    }
    def "testing updating book when bookid is valid to make an update"(){
        given:
        Integer bookId=1
        Book book = new Book(1, "Speaking JavaScript", "Axel Rauschmayer", 460, "Like it or not, JavaScript is everywhere ", new Date(), 1232.00)
        Book updatedBook = new Book(1, "Speaking ", "Axel ", 460, "Like it ", new Date(), 1232.00)
        bookRepository.findByBookId(bookId) >> Optional.of(book)
        bookRepository.save(_)>> updatedBook
        BookDto bookDto= new BookDto(updatedBook.getBookId(),updatedBook.getTitle(),
                updatedBook.getAuthor(),updatedBook.getPages(),updatedBook.getDescription(),updatedBook.getPublished(),updatedBook.getPrice());

        when:
        def result=bookService.updateBookDetails(updatedBook,bookId)
        then:
        result == bookDto
    }

    def "testing updating book when bookid is invalid to make an update"(){
        given:
        Integer bookId=1
        Book updatedBook = new Book(1, "Speaking ", "Axel ", 460, "Like it ", new Date(), 1232.00)
        bookRepository.findByBookId(bookId) >> Optional.empty()

        when:
        def result=bookService.updateBookDetails(updatedBook,bookId)
        then:
        thrown(DataNotFoundException)
    }

}