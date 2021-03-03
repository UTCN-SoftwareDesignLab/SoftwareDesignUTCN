package services;

import model.Book;
import repositories.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = findById(id);
        LocalDate publishedDate = book.getPublishedDate();

        return (int) ChronoUnit.YEARS
                .between(publishedDate.withMonth(1).atStartOfDay(), LocalDate.now().atStartOfDay());
    }
}
