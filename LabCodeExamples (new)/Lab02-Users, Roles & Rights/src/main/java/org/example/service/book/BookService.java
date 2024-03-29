package org.example.service.book;

import org.example.model.book.Book;
import org.example.repository.book.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookService {

  private final BookRepository repository;

  public BookService(BookRepository repository) {
    this.repository = repository;
  }

  public List<Book> findAll() {
    return repository.findAll();
  }

  public Book findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Book with id %d not found".formatted(id)));
  }

  public Book create(Book book) throws SQLException {
    return repository.create(book);
  }

  public int getAgeOfBook(Long id) {
    return findById(id).getAge();
  }
}
