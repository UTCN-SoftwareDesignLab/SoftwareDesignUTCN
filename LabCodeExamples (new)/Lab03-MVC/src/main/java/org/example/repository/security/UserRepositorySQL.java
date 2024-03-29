package org.example.repository.security;

import org.example.model.security.User;
import org.example.model.security.UserBuilder;
import org.example.model.validation.Notification;

import java.sql.*;

import static org.example.database.Constants.TABLES.USER;

public class UserRepositorySQL implements UserRepository {

  private final Connection connection;
  private final RoleRepository roleRepository;

  public UserRepositorySQL(Connection connection, RoleRepository roleRepository) {
    this.connection = connection;
    this.roleRepository = roleRepository;
  }

  @Override
  // todo: implement with optional
  public Notification<User> findByUsernameAndPassword(String username, String password) {
    Notification<User> resultNotification = new Notification<>();
    try {
      Statement statement = connection.createStatement();

      String fetchUserSql =
          "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
      ResultSet userResultSet = statement.executeQuery(fetchUserSql);
      if (userResultSet.next()) {
        User user = new UserBuilder()
            .setUsername(userResultSet.getString("username"))
            .setPassword(userResultSet.getString("password"))
            .setRoles(roleRepository.findRolesForUser(userResultSet.getLong("id")))
            .build();
        resultNotification.setResult(user);
        return resultNotification;
      } else {
        resultNotification.addError("Invalid username or password.");
      }
    } catch (SQLException e) {
      System.out.println(e);
      resultNotification.addError("Something is wrong with the database.");
    }
    return resultNotification;
  }

  @Override
  public User create(User user) throws SQLException {
    try {
      PreparedStatement insertUserStatement = connection
          .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      insertUserStatement.setString(1, user.getUsername());
      insertUserStatement.setString(2, user.getPassword());
      insertUserStatement.executeUpdate();

      ResultSet rs = insertUserStatement.getGeneratedKeys();
      rs.next();
      long userId = rs.getLong(1);
      user.setId(userId);

      roleRepository.addRolesToUser(user, user.getRoles());

      return user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException(e);
    }
  }

  @Override
  public void deleteAll() {
    try {
      Statement statement = connection.createStatement();
      String sql = "DELETE from user where id >= 0";
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
