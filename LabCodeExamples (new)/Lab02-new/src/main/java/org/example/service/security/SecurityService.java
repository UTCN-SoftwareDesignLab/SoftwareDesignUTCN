package org.example.service.security;

import org.example.model.security.ERole;
import org.example.model.security.Role;
import org.example.model.security.User;
import org.example.model.security.UserBuilder;
import org.example.repository.security.RoleRepository;
import org.example.repository.security.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;

public class SecurityService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public SecurityService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public User register(String username, String password) throws SQLException {
    String encodedPassword = encodePassword(password);

    Role customerRole = roleRepository.findRoleByTitle(ERole.CUSTOMER);

    User user = new UserBuilder()
        .setUsername(username)
        .setPassword(encodedPassword)
        .setRoles(List.of(customerRole))
        .build();
    //todo: link with user_role
    return userRepository.create(user);
  }

  public User login(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, encodePassword(password));
  }

  private String encodePassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder hexString = new StringBuilder();

      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
