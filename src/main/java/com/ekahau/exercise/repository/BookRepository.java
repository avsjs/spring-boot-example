package com.ekahau.exercise.repository;

import com.ekahau.exercise.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
