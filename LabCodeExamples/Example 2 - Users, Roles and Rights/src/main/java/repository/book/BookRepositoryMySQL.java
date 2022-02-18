package repository.book;

import model.Book;
import model.builder.BookBuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMySQL implements BookRepository {

  private final Connection connection;

  public BookRepositoryMySQL(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Book> findAll() {
    List<Book> books = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      String sql = "Select * from book";
      ResultSet rs = statement.executeQuery(sql);

      while (rs.next()) {
        books.add(getBookFromResultSet(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public boolean save(Book book) {
    try {
      PreparedStatement insertStatement = connection
          .prepareStatement("INSERT INTO book values (null, ?, ?, ?)");
      insertStatement.setString(1, book.getAuthor());
      insertStatement.setString(2, book.getTitle());
      insertStatement.setDate(3, new Date(book.getPublishedDate().getTime()));
      insertStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void removeAll() {
    try {
      Statement statement = connection.createStatement();
      String sql = "DELETE from book where id >= 0";
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Book getBookFromResultSet(ResultSet rs) throws SQLException {
    return new BookBuilder()
        .setId(rs.getLong("id"))
        .setTitle(rs.getString("title"))
        .setAuthor(rs.getString("author"))
        .setPublishedDate(new Date(rs.getDate("publishedDate").getTime()))
        .build();
  }

}
