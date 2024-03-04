package org.example.database;

public class DatabaseConnectionFactory {

  private static final String TEST_SCHEMA = "sd-basics-test";
  private static final String SCHEMA = "sd-basics";

  public static DbConnection getConnectionWrapper(SupportedDatabase db, boolean test) {
    final String schema = test ? TEST_SCHEMA : SCHEMA;
    switch (db) {
      case MYSQL:
        return new JDBConnectionWrapper(schema);
      default:
        throw new IllegalArgumentException("Unsupported database: " + db);
    }
  }

}
