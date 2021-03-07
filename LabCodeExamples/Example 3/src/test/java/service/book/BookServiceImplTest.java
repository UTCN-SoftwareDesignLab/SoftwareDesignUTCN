package service.book;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.book.BookRepositoryMock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookServiceImplTest {

    private BookService bookService;

    @Before
    public void setup() {
        bookService = new BookServiceImpl(new BookRepositoryMock());
    }

    @Test
    public void findAll() {
        assertEquals(0, bookService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        bookService.findById(1L);
    }

    @Test
    public void save() {
        assertTrue(bookService.save(new Book()));
    }

}