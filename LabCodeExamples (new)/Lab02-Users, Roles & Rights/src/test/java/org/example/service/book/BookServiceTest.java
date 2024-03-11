package org.example.service.book;

import org.example.model.Book;
import org.example.model.BookBuilder;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

  private BookService bookService;

  @BeforeAll
  public void setup() {
    BookRepository bookRepository =
//        new BookRepositorySQL(DatabaseConnectionFactory.getConnectionWrapper(SupportedDatabase.MYSQL, true).getConnection());
        new BookRepositoryMock();
    bookService = new BookService(bookRepository);
  }

  @Test
  void getAgeOfBook() {
    int oliverTwistPublishingYear = 1838;
    long bookId = 10L;
    Book oliverTwist = new BookBuilder()
        .setId(bookId)
        .setAuthor("Charles Dickens")
        .setTitle("Oliver Twist")
        .setPublishedDate(LocalDate.now().withYear(oliverTwistPublishingYear)).build();

    bookService.create(oliverTwist);

    assertEquals(LocalDate.now().getYear() - 1838, bookService.getAgeOfBook(bookId));
  }

  @Test
  void getAgeOfNotFoundBook() {
    long bookId = 10L;
    assertThrows(IllegalArgumentException.class, () -> bookService.getAgeOfBook(bookId));
  }
}