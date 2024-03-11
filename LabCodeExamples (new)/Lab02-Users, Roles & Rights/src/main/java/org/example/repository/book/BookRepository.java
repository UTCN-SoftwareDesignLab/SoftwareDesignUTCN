package org.example.repository.book;

import org.example.model.book.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

  List<Book> findAll();

  Optional<Book> findById(Long id);

  Book create(Book book) throws SQLException;

  void removeAll();

}
