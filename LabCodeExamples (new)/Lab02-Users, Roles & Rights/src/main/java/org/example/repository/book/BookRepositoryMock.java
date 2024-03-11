package org.example.repository.book;

import org.example.model.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository {

  private final List<Book> books;

  public BookRepositoryMock() {
    books = new ArrayList<>();
  }

  @Override
  public List<Book> findAll() {
    return books;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return books.stream()
        .filter(it -> it.getId().equals(id))
        .findFirst();
  }

  @Override
  public Book create(Book book) {
    books.add(book);
    return book;
  }

  @Override
  public void removeAll() {
    books.clear();
  }
}
