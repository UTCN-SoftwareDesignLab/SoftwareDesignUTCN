package org.example;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.DbConnection;
import org.example.model.Book;
import org.example.model.BookBuilder;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryCacheDecorator;
import org.example.repository.BookRepositorySQL;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.database.SupportedDatabase.MYSQL;

public class Main {
  public static void main(String[] args) throws SQLException {
    DbConnection connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(MYSQL, true);

    if (connectionWrapper.testConnection()) {
      System.out.println("Connection successful");
    } else {
      System.out.println("Connection failed");
    }

    Book book = new BookBuilder().setAuthor("John Doe").setTitle("Java for Dummies").setPublishedDate(
        LocalDate.now()
    ).build();

    BookRepositorySQL mysqlRepository = new BookRepositorySQL(connectionWrapper.getConnection());
    BookRepository bookRepository = new BookRepositoryCacheDecorator(mysqlRepository);
  }
}