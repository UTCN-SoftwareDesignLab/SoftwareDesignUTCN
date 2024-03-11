package org.example.service.book;

import org.example.model.Book;
import org.example.repository.book.BookRepository;

import java.util.List;

public class BookService {

  private final BookRepository repository;


  public BookService(BookRepository bookRepository) {
    this.repository = bookRepository;
  }

  public List<Book> findAll() {
    return repository.findAll();
  }

  public Book findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Book with id %d not found".formatted(id)));
  }

  public boolean create(Book book) {
    return repository.create(book);
  }

  public int getAgeOfBook(Long id) {
    return repository.findById(id)
        .map(Book::getAge)
        .orElseThrow(() -> new IllegalArgumentException("Book with id %d not found".formatted(id)));
  }
}
