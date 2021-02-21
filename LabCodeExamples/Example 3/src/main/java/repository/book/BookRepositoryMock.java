package repository.book;

import model.Book;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookRepositoryMock implements BookRepository {

    private List<Book> books;

    public BookRepositoryMock() {
        books = new ArrayList<>();
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) throws EntityNotFoundException {
        List<Book> filteredBooks = books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredBooks.size() > 0) {
            return filteredBooks.get(0);
        }
        throw new EntityNotFoundException(id, Book.class.getSimpleName());
    }

    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }
}
