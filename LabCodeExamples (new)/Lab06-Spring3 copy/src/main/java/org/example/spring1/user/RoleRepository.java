package org.example.spring1.user;

import org.example.spring1.user.model.ERole;
import org.example.spring1.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole role);

}
