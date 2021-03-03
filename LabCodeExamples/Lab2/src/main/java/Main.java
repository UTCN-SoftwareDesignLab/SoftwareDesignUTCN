import database.DatabaseConnectionFactory;
import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        Book b = new Book();
        b.setAuthor("Tolkien");
        b.setTitle("The Lord of the Rings");
        b.setId(-1L);
        b.setPublishedDate(LocalDate.now());

        Book windsOfWinter = new BookBuilder()
                .setId(-1L)
                .setAuthor("George R.R. Martin")
                .setTitle("Game of Thrones - Winds of Winter")
                .setPublishedDate(LocalDate.now().withYear(2070))
                .build();

        JDBConnectionWrapper connection = DatabaseConnectionFactory.getConnectionWrapper(true);
        boolean res = connection.testConnection();

        if (res) {
            System.out.println("All good");
        } else {
            System.out.println("Bad connection");
        }
    }
}
