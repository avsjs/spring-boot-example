package com.ekahau.exercise.controller;

public class BookControllerConstants {
    final static String PATH_URI = "/api/v1";
    final static String HELLO_SERVICE_SUMMARY = "Hello service";
    final static String ALL_BOOKS_SUMMARY = "Get list of the books";
    final static String ALL_BOOKS_NOTES = "Get List of the books with details";
    final static String BOOK_BY_ID_SUMMARY = "Get Book details based on id";
    final static String BOOK_BY_ID_NOTES = "Get Book details based on id, if book details not present with the provided id it will return BookNotFoundException.";
    final static String DELETE_BOOK_NOTES = "Remove book details based on id";
    final static String DELETE_BOOK_SUMMARY = "Delete service is used to remove book details based on id";
    final static String CREATE_BOOK_NOTES = "Create Book Data";
    final static String CREATE_BOOK_SUMMARY = "Create a new Book data";
}
