package service.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;

import static database.Constants.Roles.CUSTOMER;

public class AuthenticationServiceMySQL implements AuthenticationService {

  private final UserRepository userRepository;
  private final RightsRolesRepository rightsRolesRepository;

  public AuthenticationServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
    this.userRepository = userRepository;
    this.rightsRolesRepository = rightsRolesRepository;
  }

  @Override
  public boolean register(String username, String password) {
    String encodedPassword = encodePassword(password);

    Role customerRole = rightsRolesRepository.findRoleByTitle(CUSTOMER);

    User user = new UserBuilder()
        .setUsername(username)
        .setPassword(encodedPassword)
        .setRoles(Collections.singletonList(customerRole))
        .build();

    return userRepository.save(user);
  }

  @Override
  public User login(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, encodePassword(password));
  }

  @Override
  public boolean logout(User user) {
    return false;
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
