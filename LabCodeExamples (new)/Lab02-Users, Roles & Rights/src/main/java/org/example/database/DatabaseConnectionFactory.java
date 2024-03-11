package org.example.database;

import static org.example.database.Constants.SCHEMAS.PRODUCTION;
import static org.example.database.Constants.SCHEMAS.TEST;

public class DatabaseConnectionFactory {


  public static DbConnection getConnectionWrapper(SupportedDatabase db, boolean test) {
    final String schema = test ? TEST : PRODUCTION;
    switch (db) {
      case MYSQL:
        return new JDBConnectionWrapper(schema);
      default:
        throw new IllegalArgumentException("Unsupported database: " + db);
    }
  }

}
