package org.example.repository.security;

import org.example.model.security.ERole;
import org.example.model.security.Role;
import org.example.model.security.User;

import java.util.List;

public interface RoleRepository {
  void create(ERole role);

  Role findRoleByTitle(ERole role);

  List<Role> findRolesForUser(long userId);

  void addRolesToUser(User user, List<Role> roles);
}
