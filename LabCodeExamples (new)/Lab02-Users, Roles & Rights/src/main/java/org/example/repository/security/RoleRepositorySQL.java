package org.example.repository.security;

import org.example.model.authentication.Role;
import org.example.model.authentication.User;

import java.sql.*;
import java.util.List;

import static org.example.database.Constants.TABLES.ROLE;

public class RoleRepositorySQL implements RoleRepository {
  private final Connection connection;

  public RoleRepositorySQL(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(String role) {
    try {
      PreparedStatement insertStatement = connection
          .prepareStatement("INSERT IGNORE INTO " + ROLE + " values (null, ?)");
      insertStatement.setString(1, role);
      insertStatement.executeUpdate();
    } catch (SQLException e) {

    }
  }

  @Override
  public Role findRoleById(Long roleId) {
    Statement statement;
    try {
      statement = connection.createStatement();
      String fetchRoleSql = "Select * from " + ROLE + " where `id`=\'" + roleId + "\'";
      ResultSet roleResultSet = statement.executeQuery(fetchRoleSql);
      roleResultSet.next();
      String roleTitle = roleResultSet.getString("role");
      return new Role(roleId, roleTitle);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Role findRoleByTitle(String role) {
    Statement statement;
    try {
      statement = connection.createStatement();
      String fetchRoleSql = "Select * from " + ROLE + " where `role`=\'" + role + "\'";
      ResultSet roleResultSet = statement.executeQuery(fetchRoleSql);
      roleResultSet.next();
      Long roleId = roleResultSet.getLong("id");
      String roleTitle = roleResultSet.getString("role");
      return new Role(roleId, roleTitle);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Role> findRolesForUser(long userId) {
    return null;
  }

  @Override
  public void addRolesToUser(User user, List<Role> roles) {

  }
}
