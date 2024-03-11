package org.example.repository.security;

import org.example.model.Role;

public interface RoleRepository {

  void create(String role);

  Role findRoleById(Long roleId);

  Role findRoleByTitle(String role);

}
