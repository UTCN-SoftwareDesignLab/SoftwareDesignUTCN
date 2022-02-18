package repositories;

import model.Book;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator {

  private final Cache<Book> cache;

  public BookRepositoryCacheDecorator(BookRepository bookRepository) {
    super(bookRepository);
    cache = new Cache<>();
  }

  @Override
  public List<Book> findAll() {
    if (cache.hasResult()) {
      return cache.load();
    }
    List<Book> allBooks = decoratedRepository.findAll();
    cache.save(allBooks);
    return allBooks;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return decoratedRepository.findById(id);
  }

  @Override
  public boolean save(Book book) {
    boolean somethingChanged = decoratedRepository.save(book);
    if (somethingChanged) {
      cache.invalidateCache();
    }
    return somethingChanged;
  }

  @Override
  public void removeAll() {
    cache.invalidateCache();
    decoratedRepository.removeAll();
  }
}
