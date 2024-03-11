package org.example.repository.book;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.DbConnection;
import org.example.model.book.Book;
import org.example.model.book.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.example.database.SupportedDatabase.MYSQL;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositorySQLTest {

  private BookRepository repository;

  @BeforeAll
  public void setupClass() {
    DbConnection connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(MYSQL, true);
    repository = new BookRepositorySQL(connectionWrapper.getConnection());
  }

  @BeforeEach
  void setup() {
    repository.removeAll();
  }

  @Test
  void findAll() {
    assertEquals(0, repository.findAll().size());
  }

  @Test
  void findById() {

  }

  @Test
  void create() {
    Book bookNoAuthor = new BookBuilder()
        .setTitle("title")
        .setPublishedDate(LocalDate.now())
        .build();

    assertFalse(repository.create(bookNoAuthor));


    Book bookNoTitle = new BookBuilder().setAuthor("author")
        .setPublishedDate(LocalDate.now())
        .build();

    assertFalse(repository.create(bookNoTitle));


    Book validBook = new BookBuilder()
        .setAuthor("author")
        .setTitle("title")
        .setPublishedDate(LocalDate.now())
        .build();

    assertTrue(repository.create(validBook));
  }

  @Test
  void removeAll() {
    repository.create(new BookBuilder()
        .setAuthor("author")
        .setTitle("title")
        .setPublishedDate(LocalDate.now())
        .build());

    repository.removeAll();

    assertEquals(0, repository.findAll().size());
  }
}