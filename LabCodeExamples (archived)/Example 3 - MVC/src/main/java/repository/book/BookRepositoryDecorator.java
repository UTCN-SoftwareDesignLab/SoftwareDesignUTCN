package repository.book;

public abstract class BookRepositoryDecorator implements BookRepository {

  protected BookRepository decoratedRepository;

  public BookRepositoryDecorator(BookRepository bookRepository) {
    this.decoratedRepository = bookRepository;
  }

}
