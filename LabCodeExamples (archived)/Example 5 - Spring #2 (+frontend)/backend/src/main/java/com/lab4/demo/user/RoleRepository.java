package com.lab4.demo.user;

import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
