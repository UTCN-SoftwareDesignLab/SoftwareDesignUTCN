package services;

import model.Book;
import repositories.BookRepository;

import java.util.List;

import static java.lang.String.format;

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
    return bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format("Book with id %d not found", id)));
  }

  @Override
  public boolean save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public int getAgeOfBook(Long id) {
    return
        bookRepository.findById(id)
            .map(Book::getAge)
            .orElse(0);
  }
}
