package repository;

import model.Book;
import model.builder.BookBuilder;
import utility.JDBConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public class BookRepositoryMySQL implements BookRepository {

    private final JDBConnectionWrapper connectionWrapper;

    public BookRepositoryMySQL(JDBConnectionWrapper connectionWrapper) {
        this.connectionWrapper = connectionWrapper;
    }

    @Override
    public List<Book> findAll() {
        Connection connection = connectionWrapper.getConnection();
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
    public Book findById(Long id) {
        return null;
    }

    @Override
    public boolean save(Book book) {
        Connection connection = connectionWrapper.getConnection();
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO book values (null, ?, ?, ?)");
            insertStatement.setString(1, book.getAuthor());
            insertStatement.setString(2, book.getTitle());
            insertStatement.setDate(3, new java.sql.Date(book.getPublishedDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        Connection connection = connectionWrapper.getConnection();
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
