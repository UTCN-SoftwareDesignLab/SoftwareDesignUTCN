package org.example.repository.security;

import org.example.model.security.User;
import org.example.model.validator.Notification;

import java.sql.SQLException;

public interface UserRepository {
  Notification<User> findByUsernameAndPassword(String username, String password);

  User create(User user) throws SQLException;

  void deleteAll();

//  Response<Boolean> existsByUsername(String email);
}
