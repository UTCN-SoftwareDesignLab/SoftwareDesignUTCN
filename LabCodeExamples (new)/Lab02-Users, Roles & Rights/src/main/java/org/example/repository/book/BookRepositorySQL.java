package org.example.repository.book;

import org.example.model.book.Book;
import org.example.model.book.BookBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositorySQL implements BookRepository {

  private final Connection connection;

  public BookRepositorySQL(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Book> findAll() {
    final String sql = "Select * from book";

    List<Book> books = new ArrayList<>();

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        books.add(getBookFromResultSet(resultSet));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return books;
  }

  @Override
  public Optional<Book> findById(Long id) {
    final String sql = "Select * from book where id = ?";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return Optional.of(getBookFromResultSet(resultSet));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public Book create(Book book) throws SQLException {
    String sql = "INSERT INTO book values (null, ?, ?, ?)";

    try {
      PreparedStatement insertStatement = connection
          .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      insertStatement.setString(1, book.getAuthor());
      insertStatement.setString(2, book.getTitle());
      insertStatement.setDate(3, Date.valueOf(book.getPublishedDate()));
      insertStatement.executeUpdate();

      ResultSet generatedKeys = insertStatement.getGeneratedKeys();
      generatedKeys.next();
      long bookId = generatedKeys.getLong(1);
      book.setId(bookId);
      return book;
    } catch (SQLException e) {
      throw new SQLException(e);
    }
  }

  @Override
  public void removeAll() {
    String sql = "DELETE from book where id >= 0";

    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  private Book getBookFromResultSet(ResultSet rs) throws SQLException {
    return new BookBuilder()
        .setId(rs.getLong("id"))
        .setTitle(rs.getString("title"))
        .setAuthor(rs.getString("author"))
        .setPublishedDate(rs.getDate("publishedDate").toLocalDate())
        .build();
  }
}
