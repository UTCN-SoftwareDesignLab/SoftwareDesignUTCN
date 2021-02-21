package service;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import repository.BookRepositoryMock;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
    public void findAll() throws Exception {
        assertEquals(0, bookService.findAll().size());
    }

    @Test
    public void findById() throws Exception {
        Long id = 1L;
        assertNull(bookService.findById(id));
    }

    @Test
    public void save() throws Exception {
        assertTrue(bookService.save(new Book()));
    }

}