package org.example.service.authentication;

import org.example.model.authentication.Role;
import org.example.model.authentication.User;
import org.example.model.authentication.UserBuilder;
import org.example.repository.security.RoleRepository;
import org.example.repository.security.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import static org.example.database.Constants.ROLES.CUSTOMER;

public class AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;


  public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public boolean register(String username, String password) {
    String encodedPassword = encodePassword(password);

    Role costumerRole = roleRepository.findRoleByTitle(CUSTOMER);

    User user = new UserBuilder()
        .setUsername(username)
        .setPassword(encodedPassword)
        .setRoles(List.of(costumerRole))
        .build();

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
