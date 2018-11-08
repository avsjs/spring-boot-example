package com.ekahau.exercise.service;

import com.ekahau.exercise.model.Book;

import java.util.List;

/**
 * Book service provides the list of books and get book details based on id.
 * Provides methods to  create a new book data and also delete data based on id.
 */

public interface BookService {

    /**
     * Returns List of books available
     *
     * @return List of Books details
     */
    List<Book> findAllBooks();

    /**
     * Returns Book details based on id
     *
     * @param id
     * @return Book details
     */
    Book getBookById(Long id);

    /**
     * Create new Book data
     *
     * @param book
     */
    void saveOrUpdate(Book book);

    /**
     * Delete Book details based on provided id
     *
     * @param id
     */
    void delete(Long id);
}
