package repository.book;

import database.DBConnectionFactory;
import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Cache;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMySQLTest {

  private static BookRepository bookRepository;

  @BeforeAll
  public static void setupClass() {
    bookRepository = new BookRepositoryCacheDecorator(
        new BookRepositoryMySQL(
            new DBConnectionFactory().getConnectionWrapper(true).getConnection()
        ),
        new Cache<>()
    );
  }

  @BeforeEach
  public void cleanUp() {
    bookRepository.removeAll();
  }

  @Test
  public void findAll() throws Exception {
    List<Book> books = bookRepository.findAll();
    assertEquals(books.size(), 0);
  }

  @Test
  public void findAllWhenDbNotEmpty() throws Exception {
    Book book = new BookBuilder()
        .setTitle("Title")
        .setAuthor("Author")
        .setPublishedDate(new Date())
        .build();
    bookRepository.save(book);
    bookRepository.save(book);
    bookRepository.save(book);

    List<Book> books = bookRepository.findAll();
    assertEquals(books.size(), 3);
  }

  @Test
  public void findById() throws Exception {

  }

  @Test
  public void save() throws Exception {
    assertTrue(bookRepository.save(
        new BookBuilder()
            .setTitle("Title")
            .setAuthor("Author")
            .setPublishedDate(new Date())
            .build()
    ));
  }

  @Test
  public void removeAll() throws Exception {

  }

}
