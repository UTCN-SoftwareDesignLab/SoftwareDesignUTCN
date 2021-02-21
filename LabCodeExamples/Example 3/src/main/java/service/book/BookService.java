package service.book;

import model.Book;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface BookService {

    List<Book> findAll();

    Book findById(Long id) throws EntityNotFoundException;

    boolean save(Book book);

    int getAgeOfBook(Long id) throws EntityNotFoundException;

}
