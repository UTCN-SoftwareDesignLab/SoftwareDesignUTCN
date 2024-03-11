package org.example.repository.security;

import org.example.model.authentication.User;

import java.util.List;

public interface UserRepository {

  List<User> findAll();

  User findByUsernameAndPassword(String username, String password);

  boolean create(User user);

  void removeAll();

//  Response<Boolean> existsByUsername(String email);

}
