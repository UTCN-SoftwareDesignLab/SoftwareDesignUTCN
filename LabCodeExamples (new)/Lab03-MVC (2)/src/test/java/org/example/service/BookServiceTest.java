package org.example.service;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.SupportedDatabase;
import org.example.model.book.Book;
import org.example.model.book.BookBuilder;
import org.example.repository.book.BookRepository;
import org.example.repository.book.BookRepositorySQL;
import org.example.service.book.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

  private BookService bookService;
  private BookRepository bookRepository;

  @BeforeEach
  public void cleanup() {
    bookRepository.removeAll();
  }

  @BeforeAll
  public void setup() {
    //        new BookRepositoryMock();
    bookRepository = new BookRepositorySQL(DatabaseConnectionFactory.getConnectionWrapper(SupportedDatabase.MYSQL, true).getConnection());
    bookService = new BookService(bookRepository);
  }

  @Test
  void getAgeOfBook() throws SQLException {
    int publishingYearOfIon = 1920;
    Book book = new BookBuilder()
        .setTitle("Ion")
        .setAuthor("Liviu Rebreanu")
        .setPublishedDate(LocalDate.now().withYear(publishingYearOfIon))
        .build();

    Book savedBook = bookService.create(book);

    int actual = bookService.getAgeOfBook(savedBook.getId());
    assertEquals(LocalDate.now().getYear() - publishingYearOfIon, actual);
  }

  @Test
  void getAgeOfNotFoundBook() {
    long bookId = 10L;
    assertThrows(IllegalArgumentException.class, () -> bookService.getAgeOfBook(bookId));
  }
}