package model.builder;

import model.Book;

import java.time.LocalDate;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookBuilder {

  private final Book book;

  public BookBuilder() {
    book = new Book();
  }

  public BookBuilder setAuthor(String author) {
    book.setAuthor(author);
    return this;
  }

  public BookBuilder setTitle(String title) {
    book.setTitle(title);
    return this;
  }

  public BookBuilder setPublishedDate(LocalDate publishedDate) {
    book.setPublishedDate(publishedDate);
    return this;
  }

  public BookBuilder setId(Long id) {
    book.setId(id);
    return this;
  }

  public Book build() {
    return book;
  }
}
