package repository.book;

/**
 * Created by Alex on 07/03/2017.
 */
public abstract class BookRepositoryDecorator implements BookRepository {

    protected BookRepository decoratedRepository;

    public BookRepositoryDecorator(BookRepository bookRepository) {
        this.decoratedRepository = bookRepository;
    }

}
