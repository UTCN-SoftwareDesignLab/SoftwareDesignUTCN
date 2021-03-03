package repositories;

import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class BookRepositoryMySQLTest {


    private static BookRepository repository;

    @BeforeClass
    public static void setupClass() {
        JDBConnectionWrapper connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(true);
        repository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper));
    }

    @Before
    public void setup() {
        repository.removeAll();
    }

    @Test
    public void findAll() {
        List<Book> noBooks = repository.findAll();
        Assert.assertTrue(noBooks.isEmpty());
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
        Book bookNoAuthor = new BookBuilder().setTitle("title")
                .setPublishedDate(LocalDate.now())
                .build();

        Assert.assertFalse(repository.save(bookNoAuthor));

        Book bookNoTitle = new BookBuilder().setAuthor("author")
                .setPublishedDate(LocalDate.now())
                .build();

        Assert.assertFalse(repository.save(bookNoTitle));

        Book validBook = new BookBuilder()
                .setAuthor("author")
                .setTitle("title")
                .setPublishedDate(LocalDate.now())
                .build();

        Assert.assertTrue(repository.save(validBook));
    }

    @Test
    public void removeAll() {
        repository.save(new BookBuilder()
                .setTitle("ceva")
                .setAuthor("autor")
                .setPublishedDate(LocalDate.now())
                .build());
        repository.removeAll();
        List<Book> noBooks = repository.findAll();
        Assert.assertTrue(noBooks.isEmpty());
    }
}