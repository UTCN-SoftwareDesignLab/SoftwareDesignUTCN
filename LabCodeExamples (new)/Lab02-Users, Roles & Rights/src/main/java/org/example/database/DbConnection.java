package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbConnection {

  protected Connection connection;

  public abstract boolean testConnection() throws SQLException;

  public Connection getConnection() {
    return connection;
  }
}
