package org.example.repository.security;

import org.example.model.authentication.User;
import org.example.model.authentication.UserBuilder;

import java.sql.*;
import java.util.List;

import static org.example.database.Constants.TABLES.USER;

public class UserRepositorySQL implements UserRepository {

  private final Connection connection;
  private final RoleRepository roleRepository;

  public UserRepositorySQL(Connection connection, RoleRepository roleRepository) {
    this.connection = connection;
    this.roleRepository = roleRepository;
  }


  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public User findByUsernameAndPassword(String username, String password) {
    try {
      Statement statement = connection.createStatement();

      String fetchUserSql =
          "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
      ResultSet userResultSet = statement.executeQuery(fetchUserSql);
      userResultSet.next();

      User user = new UserBuilder()
          .setUsername(userResultSet.getString("username"))
          .setPassword(userResultSet.getString("password"))
          .setRoles(roleRepository.findRolesForUser(userResultSet.getLong("id")))
          .build();

      return user;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public boolean create(User user) {
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

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
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
