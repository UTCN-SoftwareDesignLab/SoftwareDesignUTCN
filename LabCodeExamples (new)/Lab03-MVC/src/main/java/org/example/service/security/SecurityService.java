package org.example.service.security;

import org.example.model.security.ERole;
import org.example.model.security.Role;
import org.example.model.security.User;
import org.example.model.security.UserBuilder;
import org.example.model.validation.Notification;
import org.example.model.validation.UserValidator;
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

  public Notification<User> register(String username, String password) {
    Role customerRole = roleRepository.findRoleByTitle(ERole.CUSTOMER);

    User user = new UserBuilder()
        .setUsername(username)
        .setPassword(password)
        .setRoles(List.of(customerRole))
        .build();

    UserValidator validator = new UserValidator(user);
    boolean validUser = validator.validate();
    Notification<User> userRegistrationNotification = new Notification<>();

    if (!validUser) {
      validator.getErrors().forEach(userRegistrationNotification::addError);
    } else {
      user.setPassword(encodePassword(password));
      User createdUser;
      try {
        createdUser = userRepository.create(user);
      } catch (SQLException e) {
        userRegistrationNotification.addError("There was something wrong with the database!");
        return userRegistrationNotification;
      }
      userRegistrationNotification.setResult(createdUser);
    }

    return userRegistrationNotification;
  }

  public Notification<User> login(String username, String password) {
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
