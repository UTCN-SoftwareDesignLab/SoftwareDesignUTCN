package org.example.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnectionWrapper extends DbConnection {

  private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/";

  private static final String USER = "root";
  private static final String PASS = "root";
  private static final int TIMEOUT = 1;

  public JDBConnectionWrapper(String schema) {
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL + schema, USER, PASS);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean testConnection() throws SQLException {
    return connection.isValid(TIMEOUT);
  }

}
