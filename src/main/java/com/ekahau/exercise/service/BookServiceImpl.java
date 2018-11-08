package com.ekahau.exercise.service;

import com.ekahau.exercise.controller.BookController;
import com.ekahau.exercise.exception.BookNotFoundException;
import com.ekahau.exercise.model.Book;
import com.ekahau.exercise.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("bookService")
public class BookServiceImpl implements BookService {
    Logger logger = LogManager.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        logger.info("Getting list of books");
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findOne(id);
        checkIfBookPresent(id, book);
        logger.info("Fetching book with Id: " + id);
        return book;
    }

    @Override
    public void saveOrUpdate(Book book) {
        bookRepository.save(book);
        logger.info("Adding new book details");
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findOne(id);
        checkIfBookPresent(id, book);
        logger.info("Deleting book with Id: " + id);
        bookRepository.delete(book);
    }

    private void checkIfBookPresent(Long id, Book book) {
        if (Objects.isNull(book)) {
            logger.error("Book not present for Id: " + id);
            throw new BookNotFoundException("Book not present for Id: " + id);
        }
    }
}
