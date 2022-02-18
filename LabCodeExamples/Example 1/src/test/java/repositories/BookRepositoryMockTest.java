package repositories;

import model.Book;
import model.builder.BookBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class BookRepositoryMockTest {

  private static BookRepository repository;

  @BeforeClass
  public static void setup() {
    repository = new BookRepositoryCacheDecorator(new BookRepositoryMock());
    System.out.println("@Before");
  }


  @Test
  public void findAll() {
    System.out.println("findAll");
    List<Book> allBooks = repository.findAll();

    Assert.assertTrue(allBooks.isEmpty());

    int nrInsertedBooks = 5;
    for (int i = 0; i < nrInsertedBooks; i++) {
      repository.save(new BookBuilder().setId(new Random().nextLong()).build());
    }

    List<Book> newBooks = repository.findAll();

    Assert.assertEquals(nrInsertedBooks, newBooks.size());
  }

  @Test
  public void findById() {
    System.out.println("findById");
    long notToBeFound = -1;
    Book notFoundBook = repository.findById(notToBeFound);
    Assert.assertNull(notFoundBook);

    long idToBeFound = 1;
    String bookTitle = "whatever";
    Book bookToBeFound = new BookBuilder().setId(idToBeFound).setTitle(bookTitle).build();
    repository.save(bookToBeFound);

    Book foundBook = repository.findById(idToBeFound);
    Assert.assertNotNull(foundBook);
    Assert.assertEquals(bookTitle, foundBook.getTitle());
  }

  @Test
  public void save() {
    long id = -5L;
    Assert.assertTrue(
        repository.save(new BookBuilder().setTitle("orice").setId(id).build())
    );
    Assert.assertNotNull(repository.findById(id));
  }

  @Test
  public void removeAll() {
    repository.removeAll();
    Assert.assertTrue(repository.findAll().isEmpty());
  }
}
