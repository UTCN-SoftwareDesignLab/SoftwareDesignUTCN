package repository.book;

import launcher.ComponentFactory;
import model.Book;
import model.builder.BookBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookRepositoryMySQLTest {

    private static BookRepository bookRepository;

    @BeforeClass
    public static void setupClass() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);

        bookRepository = new BookRepositoryCacheDecorator(
                componentFactory.getBookRepositoryMySQL(),
                new Cache<>()
        );
    }

    @Before
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
    public void findById() {

    }

    @Test
    public void save() {
        assertTrue(bookRepository.save(
                new BookBuilder()
                        .setTitle("Title")
                        .setAuthor("Author")
                        .setPublishedDate(new Date())
                        .build()
        ));
    }

    @Test
    public void removeAll() {

    }

}