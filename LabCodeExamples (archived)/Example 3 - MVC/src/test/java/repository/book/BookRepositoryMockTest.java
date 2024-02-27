package repository.book;

import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.Cache;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMockTest {

  private static BookRepository repository;

  @BeforeAll
  public static void setupClass() {
    repository = new BookRepositoryCacheDecorator(
        new BookRepositoryMock(),
        new Cache<>()
    );
  }

  @Test
  public void findAll() throws Exception {
    assertEquals(0, repository.findAll().size());
  }

  @Test
  public void findById() throws Exception {
    final Optional<Book> book = repository.findById(1L);
    assertTrue(book.isEmpty());
  }

  @Test
  public void save() throws Exception {
    Book book = new BookBuilder()
        .setId(1L)
        .setTitle("Title")
        .setAuthor("Author")
        .setPublishedDate(new Date())
        .build();

    assertTrue(repository.save(book));
  }

}
