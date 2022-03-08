package service.book;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.book.BookRepositoryMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceImplTest {

  private BookService bookService;

  @BeforeEach
  public void setup() {
    bookService = new BookServiceImpl(new BookRepositoryMock());
  }

  @Test
  public void findAll() throws Exception {
    assertEquals(0, bookService.findAll().size());
  }

  @Test
  public void findById() throws Exception {
    Long id = 1L;
    assertThrows(IllegalArgumentException.class, () -> bookService.findById(id));
  }

  @Test
  public void save() throws Exception {
    assertTrue(bookService.save(new Book()));
  }

}
