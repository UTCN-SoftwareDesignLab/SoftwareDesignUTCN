package repository.book;

import model.Book;
import repository.Cache;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator {

  private Cache<Book> cache;

  public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache) {
    super(bookRepository);
    this.cache = cache;
  }

  @Override
  public List<Book> findAll() {
    if (cache.hasResult()) {
      return cache.load();
    }
    List<Book> books = decoratedRepository.findAll();
    cache.save(books);
    return books;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return decoratedRepository.findById(id);
  }

  @Override
  public boolean save(Book book) {
    cache.invalidateCache();
    return decoratedRepository.save(book);
  }

  @Override
  public void removeAll() {
    cache.invalidateCache();
    decoratedRepository.removeAll();
  }
}
