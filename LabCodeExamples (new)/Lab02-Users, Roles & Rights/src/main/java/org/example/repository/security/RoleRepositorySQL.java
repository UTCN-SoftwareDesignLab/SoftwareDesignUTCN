package org.example.repository.security;

import org.example.model.authentication.Role;
import org.example.model.authentication.User;

import java.sql.Connection;
import java.util.List;

public class RoleRepositorySQL implements RoleRepository {
  private final Connection connection;

  public RoleRepositorySQL(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(String role) {

  }

  @Override
  public Role findRoleById(Long roleId) {
    return null;
  }

  @Override
  public Role findRoleByTitle(String role) {
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
