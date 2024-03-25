package org.example.repository.security;

import org.example.model.security.ERole;
import org.example.model.security.Role;
import org.example.model.security.User;

import java.sql.*;
import java.util.List;

import static org.example.database.Constants.TABLES.ROLE;

public class RoleRepositorySQL implements RoleRepository {
  private final Connection connection;

  public RoleRepositorySQL(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(ERole role) {
    try {
      PreparedStatement insertStatement = connection
          .prepareStatement("INSERT IGNORE INTO " + ROLE + " values (null, ?)");
      insertStatement.setString(1, role.toString());
      insertStatement.executeUpdate();
    } catch (SQLException e) {

    }
  }

  @Override
  // todo: change to optional
  public Role findRoleByTitle(ERole role) {
    Statement statement;
    try {
      statement = connection.createStatement();
      String fetchRoleSql = "Select * from " + ROLE + " where `role`=\'" + role.toString() + "\'";
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
  // todo: implement
  public List<Role> findRolesForUser(long userId) {
    return null;
  }

  @Override
  // todo: implement
  public void addRolesToUser(User user, List<Role> roles) {

  }
}
