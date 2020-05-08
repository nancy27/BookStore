package com.online.BookStore.repository;

import com.online.BookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByBookId(Integer bookId);

    void deleteByBookId(Integer bookId);

    Optional<Book> findByTitle(String title);
}
