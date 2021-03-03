package services;

import model.Book;
import model.builder.BookBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repositories.BookRepository;
import repositories.BookRepositoryMock;

import java.time.LocalDate;

public class BookServiceImplTest {

    private static BookService service;

    @BeforeClass
    public static void setupClass() {
        BookRepository bookRepositoryMock =
                new BookRepositoryMock();
        service = new BookServiceImpl(bookRepositoryMock);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void getAgeOfBook() {
        int publishingYear = 2001;
        long id = 20;
        Book book = new BookBuilder()
                .setId(id)
                .setTitle("numantereseaza")
                .setAuthor("oricare")
                .setPublishedDate(LocalDate.of(publishingYear, 5, 21))
                .build();
        service.save(book);

        int ageOfBook = service.getAgeOfBook(id);
        Assert.assertEquals(LocalDate.now().getYear() - publishingYear, ageOfBook);
    }
}