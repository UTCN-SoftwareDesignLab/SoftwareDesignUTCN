package service.book;

import model.Book;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    int getAgeOfBook(Long id);

}
