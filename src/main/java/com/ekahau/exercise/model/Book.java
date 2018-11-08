package com.ekahau.exercise.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

@Entity
@ValidateOnExecution
public class Book {

    @Id
    @GeneratedValue
    @ApiModelProperty(readOnly = true, hidden = true)
    private Long id;
    @NotNull
    @Valid
    @Size(min=2, message="{book.title.invalid}")
    private String title;
    @NotNull
    @Valid
    @Size(min=2, message="{book.author.invalid}")
    private String author;
    @NotNull
    @Min(value = 1300, message = "{book.year.invalid}")
    @Max(value = 2018, message = "{book.year.invalid}")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
