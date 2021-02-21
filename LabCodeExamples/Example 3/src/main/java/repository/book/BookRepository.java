package repository.book;

import model.Book;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface BookRepository {

    List<Book> findAll();

    Book findById(Long id) throws EntityNotFoundException;

    boolean save(Book book);

    void removeAll();

}
