package org.example.repository.book;

import org.example.model.Book;
import org.example.repository.Cache;

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
    if (cache.hasResult()) {
      return cache.load().stream()
          .filter(book -> book.getId().equals(id))
          .findFirst();
    }
    final Optional<Book> result = decoratedRepository.findById(id);
    if (result.isPresent()) {
      cache.add(result.get());
      return result;
    }
    return Optional.empty();
  }

  @Override
  public boolean create(Book book) {
    boolean res = decoratedRepository.create(book);
    cache.add(book);
    return res;
  }

  @Override
  public void removeAll() {
    decoratedRepository.removeAll();
    cache.invalidateCache();
  }
}
