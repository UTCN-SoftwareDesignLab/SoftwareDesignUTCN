package org.example.repository.book;

import org.example.model.Book;
import org.example.model.BookBuilder;

import java.sql.*;
import java.time.LocalDate;
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
    return Optional.empty();
  }

  @Override
  public boolean create(Book book) {
    String sql = "INSERT INTO book values (null, ?, ?, ?)";

    try {
      PreparedStatement insertStatement = connection
          .prepareStatement(sql);
      insertStatement.setString(1, book.getAuthor());
      insertStatement.setString(2, book.getTitle());
      insertStatement.setDate(3, new java.sql.Date(book.getPublishedDate().toEpochDay()));
      insertStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      return false;
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
        .setPublishedDate(LocalDate.ofEpochDay(rs.getDate("publishedDate").getTime()))
        .build();
  }
}
