package com.ekahau.excerise.service;

import com.ekahau.exercise.model.Book;
import com.ekahau.exercise.repository.BookRepository;
import com.ekahau.exercise.service.BookService;
import com.ekahau.exercise.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceImplIntegrationTest {

    @TestConfiguration
    static class BookServiceImplTestContextConfiguration {

        @Bean
        public BookService bookService() {
            return new BookServiceImpl();
        }
    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.bookService = Mockito.mock(BookService.class);
    }

    @Test
    public void findAllBooks() {
        List<Book> books = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(books);
        List<Book> allBooks = bookService.findAllBooks();
        verifyNoMoreInteractions(bookRepository);
        assertEquals(books, allBooks);
    }

    @Test
    public void findById() {
        when(bookService.getBookById(anyLong())).thenReturn(new Book());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void saveOrUpdate() {
        Book book = new Book("Pride and Prejudice", "Jane Austen", 1813);
        when(bookRepository.save(book)).thenReturn(book);
        verifyNoMoreInteractions(bookRepository);
    }
}
