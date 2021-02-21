package repository.book;

import model.Book;
import model.builder.BookBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMock;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 08/03/2017.
 */
public class BookRepositoryMockTest {

    private static BookRepository repository;

    @BeforeClass
    public static void setupClass() {
        repository = new BookRepositoryCacheDecorator(
                new BookRepositoryMock(),
                new Cache<>()
        );
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        assertTrue(repository.findAll().size() == 0);
    }

    @Test
    public void findById() throws Exception {
        Book book = repository.findById(1L);
        assertNull(book);
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