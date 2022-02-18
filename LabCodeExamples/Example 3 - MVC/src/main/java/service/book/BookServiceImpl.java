package service.book;

import model.Book;
import repository.book.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  public BookServiceImpl(BookRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Book> findAll() {
    return repository.findAll();
  }

  @Override
  public Book findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Book with id %d not found".formatted(id)));
  }

  @Override
  public boolean save(Book book) {
    return repository.save(book);
  }

  @Override
  public int getAgeOfBook(Long id) {
    return repository.findById(id)
        .map(Book::getAge)
        .orElse(0);
  }


}
