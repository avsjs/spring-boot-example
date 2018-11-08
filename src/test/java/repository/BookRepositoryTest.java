package repository;

import com.ekahau.exercise.Main;
import com.ekahau.exercise.model.Book;
import com.ekahau.exercise.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void saveOrUpdate() {
        Book book = new Book("Test", "Ekahau", 2017);
        bookRepository.save(book);
        assertNotEquals(book.getTitle(), "testw");
    }

    @Test
    public void findAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        assertNotNull(books);
    }

    @Test
    public void getBookWithInvalidId() {
        Book book = bookRepository.findOne(10000L);
        assertNull(book);
    }

    @Test
    public void deleteBookWithValidId() {
        Book book = bookRepository.findOne(1000001L);
        bookRepository.delete(1000001L);
        Book deletedBook = bookRepository.findOne(1000001L);
        assertNotEquals(book, deletedBook);
    }
}