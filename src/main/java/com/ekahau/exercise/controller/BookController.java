package com.ekahau.exercise.controller;

import com.ekahau.exercise.model.Book;
import com.ekahau.exercise.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.ekahau.exercise.controller.BookControllerConstants.ALL_BOOKS_NOTES;
import static com.ekahau.exercise.controller.BookControllerConstants.ALL_BOOKS_SUMMARY;
import static com.ekahau.exercise.controller.BookControllerConstants.BOOK_BY_ID_NOTES;
import static com.ekahau.exercise.controller.BookControllerConstants.BOOK_BY_ID_SUMMARY;
import static com.ekahau.exercise.controller.BookControllerConstants.CREATE_BOOK_NOTES;
import static com.ekahau.exercise.controller.BookControllerConstants.CREATE_BOOK_SUMMARY;
import static com.ekahau.exercise.controller.BookControllerConstants.DELETE_BOOK_NOTES;
import static com.ekahau.exercise.controller.BookControllerConstants.DELETE_BOOK_SUMMARY;
import static com.ekahau.exercise.controller.BookControllerConstants.HELLO_SERVICE_SUMMARY;
import static com.ekahau.exercise.controller.BookControllerConstants.PATH_URI;

@RestController
@Validated
public class BookController {
    Logger logger = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 201, message = "Data created successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @RequestMapping(path = "/hello/{callerName}", method = RequestMethod.GET)
    @ApiOperation(value = HELLO_SERVICE_SUMMARY, response = String.class)
    public ResponseEntity<String> sayHello(@PathVariable("callerName") String callerName) {
        return new ResponseEntity<String>(String.format("Hello, %s!", callerName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_URI + "/books", produces = "application/json")
    @ApiOperation(value = ALL_BOOKS_SUMMARY, notes = ALL_BOOKS_NOTES, response = Iterable.class, responseContainer = "List")
    private List<Book> getAllBooks() {
        logger.info("Started executing getAllBooks method");
        return bookService.findAllBooks();
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_URI + "/books/{id}", produces = "application/json")
    @ApiOperation(value = BOOK_BY_ID_SUMMARY, notes = BOOK_BY_ID_NOTES, response = Book.class)
    private Book getBookDetailsById(@Valid @PathVariable("id") Long id) {
        logger.info("Started executing getBookDetailsById method");
        return bookService.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = PATH_URI + "/books/{id}")
    @ApiOperation(value = DELETE_BOOK_SUMMARY, notes = DELETE_BOOK_NOTES)
    private void deleteBookDetailsById(@Valid @PathVariable("id") Long id) {
        logger.info("Started executing deleteBookDetailsById method");
        bookService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = PATH_URI + "/books", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = CREATE_BOOK_SUMMARY, notes = CREATE_BOOK_NOTES)
    private @ResponseBody
    Long saveBookDetails(@Valid @RequestBody Book book) {
        logger.info("Started executing deleteBookDetailsById method");
        bookService.saveOrUpdate(book);
        return book.getId();
    }
}
