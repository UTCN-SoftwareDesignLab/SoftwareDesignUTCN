package repository.book;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository {

  private List<Book> books;

  public BookRepositoryMock() {
    books = new ArrayList<>();
  }

  public List<Book> findAll() {
    return books;
  }

  public Optional<Book> findById(Long id) {
    return books.parallelStream()
        .filter(it -> it.getId().equals(id))
        .findFirst();
  }

  public boolean save(Book book) {
    return books.add(book);
  }

  @Override
  public void removeAll() {
    books.clear();
  }
}
