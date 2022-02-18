package repositories;

import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMockTest {

  private static BookRepository repository;

  @BeforeAll
  public static void setup() {
    repository = new BookRepositoryCacheDecorator(new BookRepositoryMock());
    System.out.println("@Before");
  }


  @Test
  public void findAll() {
    System.out.println("findAll");
    List<Book> allBooks = repository.findAll();

    assertTrue(allBooks.isEmpty());

    int nrInsertedBooks = 5;
    for (int i = 0; i < nrInsertedBooks; i++) {
      repository.save(new BookBuilder().setId(new Random().nextLong()).build());
    }

    List<Book> newBooks = repository.findAll();

    assertEquals(nrInsertedBooks, newBooks.size());
  }

  @Test
  public void findById() {
    System.out.println("findById");
    long notToBeFound = -1;
    Optional<Book> notFoundBook = repository.findById(notToBeFound);
    assertTrue(notFoundBook.isEmpty());

    long idToBeFound = 1;
    String bookTitle = "whatever";
    Book bookToBeFound = new BookBuilder().setId(idToBeFound).setTitle(bookTitle).build();
    repository.save(bookToBeFound);

    Optional<Book> foundBook = repository.findById(idToBeFound);
    assertFalse(foundBook.isEmpty());
    assertEquals(bookTitle, foundBook.get().getTitle());
  }

  @Test
  public void save() {
    long id = -5L;
    assertTrue(
        repository.save(new BookBuilder().setTitle("orice").setId(id).build())
    );
    assertNotNull(repository.findById(id));
  }

  @Test
  public void removeAll() {
    repository.removeAll();
    assertTrue(repository.findAll().isEmpty());
  }
}
