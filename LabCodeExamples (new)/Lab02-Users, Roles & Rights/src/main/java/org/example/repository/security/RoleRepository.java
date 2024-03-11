package org.example.repository.security;

import org.example.model.authentication.Role;
import org.example.model.authentication.User;

import java.util.List;

public interface RoleRepository {

  void create(String role);

  Role findRoleByTitle(String role);

  List<Role> findRolesForUser(long userId);

  void addRolesToUser(User user, List<Role> roles);
}
