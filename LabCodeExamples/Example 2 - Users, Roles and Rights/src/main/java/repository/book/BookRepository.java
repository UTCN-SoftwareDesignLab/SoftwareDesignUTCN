package repository.book;

import model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alex on 07/03/2017.
 */
public interface BookRepository {

  List<Book> findAll();

  Optional<Book> findById(Long id);

  boolean save(Book book);

  void removeAll();

}
