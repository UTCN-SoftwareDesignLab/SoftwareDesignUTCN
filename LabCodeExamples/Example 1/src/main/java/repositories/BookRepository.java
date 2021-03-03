package repositories;

import model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    void removeAll();

}
