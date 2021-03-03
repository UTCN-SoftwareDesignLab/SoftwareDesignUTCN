package service;

import model.Book;
import model.builder.BookBuilder;
import org.junit.Before;
import org.junit.Test;
import repository.BookRepositoryMock;

import java.time.LocalDate;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookServiceImplTest {

    private BookService service;
    private BookRepositoryMock repository;

    @Before
    public void setup() {
        repository = new BookRepositoryMock();
        service = new BookServiceImpl(repository);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void findById() throws Exception {
        Long id = 1L;
        assertNull(service.findById(id));
    }

    @Test
    public void save() throws Exception {
        assertTrue(service.save(new Book()));
    }

    @Test
    public void getAgeOfBook() {
        int publishingYear = 2001;
        long id = 1L;
        Book book = new BookBuilder()
                .setId(id)
                .setAuthor("Author")
                .setTitle("Title")
                .setPublishedDate(LocalDate.of(publishingYear, 5, 21))
                .build();
        repository.save(book);

        Book foundBook = repository.findById(id);

        assertNotNull(foundBook);
        int ageOfBook = service.getAgeOfBook(foundBook.getId());
        assertEquals(LocalDate.now().getYear() - publishingYear, ageOfBook);
    }
}