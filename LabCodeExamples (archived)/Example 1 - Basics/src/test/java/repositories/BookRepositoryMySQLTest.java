package repositories;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMySQLTest {


  private static BookRepository repository;

  @BeforeAll
  public static void setupClass() {
    JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
    repository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper.getConnection()));
  }

  @BeforeEach
  public void setup() {
    repository.removeAll();
  }

  @Test
  public void findAll() {
    List<Book> noBooks = repository.findAll();
    assertTrue(noBooks.isEmpty());
  }

  @Test
  public void findById() {
  }

  @Test
  public void save() {
    Book bookNoAuthor = new BookBuilder().setTitle("title")
        .setPublishedDate(LocalDate.now())
        .build();

    assertFalse(repository.save(bookNoAuthor));

    Book bookNoTitle = new BookBuilder().setAuthor("author")
        .setPublishedDate(LocalDate.now())
        .build();

    assertFalse(repository.save(bookNoTitle));

    Book validBook = new BookBuilder()
        .setAuthor("author")
        .setTitle("title")
        .setPublishedDate(LocalDate.now())
        .build();

    assertTrue(repository.save(validBook));
  }

  @Test
  public void removeAll() {
    repository.save(new BookBuilder()
        .setTitle("ceva")
        .setAuthor("autor")
        .setPublishedDate(LocalDate.now())
        .build());
    repository.removeAll();
    List<Book> noBooks = repository.findAll();
    assertTrue(noBooks.isEmpty());
  }
}
