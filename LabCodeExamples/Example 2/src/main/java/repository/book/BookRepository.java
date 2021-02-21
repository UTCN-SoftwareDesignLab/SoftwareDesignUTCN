package repository.book;

import model.Book;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface BookRepository {

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    void removeAll();

}
