package org.example.database;

import org.example.repository.security.RoleRepository;
import org.example.repository.security.RoleRepositorySQL;
import org.example.repository.security.UserRepository;
import org.example.repository.security.UserRepositorySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static org.example.database.Constants.SCHEMAS.SCHEMAS;


public class Bootstrap {

  private RoleRepository roleRepository;
  private UserRepository userRepository;

  public void boostrap() throws SQLException {
    dropAll();

    createTables();

    createUserData();
  }

  private void dropAll() throws SQLException {
    for (String schema : SCHEMAS) {
      System.out.println("Dropping all tables in schema: " + schema);

      Connection connection = new JDBConnectionWrapper(schema).getConnection();
      Statement statement = connection.createStatement();


      String[] dropStatements = {
          "TRUNCATE `user_role`;",
          "DROP TABLE `user_role`;",
          "TRUNCATE `role`;",
          "DROP TABLE  `book`, `role`, `user`;"
      };

      Arrays.stream(dropStatements).forEach(dropStatement -> {
        try {
          statement.execute(dropStatement);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }

    System.out.println("Done table bootstrap");
  }

  private void createTables() throws SQLException {
    SQLTableCreationFactory sqlTableCreationFactory = new SQLTableCreationFactory();

    for (String schema : SCHEMAS) {
      System.out.println("Bootstrapping " + schema + " schema");

      JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
      Connection connection = connectionWrapper.getConnection();

      Statement statement = connection.createStatement();

      for (String table : Constants.TABLES.ORDERED_TABLES_FOR_CREATION) {
        String createTableSQL = sqlTableCreationFactory.getCreateSQLForTable(table);
        statement.execute(createTableSQL);
      }
    }

    System.out.println("Done table bootstrap");
  }

  private void createUserData() {
    for (String schema : SCHEMAS) {
      System.out.println("Bootstrapping user data for " + schema);

      createRoles(schema);
      createUsers(schema);
    }
  }

  private void createRoles(String schema) {
    JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
    roleRepository = new RoleRepositorySQL(connectionWrapper.getConnection());
    for (String role : Constants.ROLES.ROLES) {
      roleRepository.create(role);
    }
  }

  private void createUsers(String schema) {
    JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
    userRepository = new UserRepositorySQL(connectionWrapper.getConnection());
    // ...
  }
}
