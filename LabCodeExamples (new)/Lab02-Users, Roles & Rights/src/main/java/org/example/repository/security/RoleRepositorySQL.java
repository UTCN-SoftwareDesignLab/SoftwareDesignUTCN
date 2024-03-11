package org.example.repository.security;

import org.example.model.Role;

import java.sql.Connection;

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
}
