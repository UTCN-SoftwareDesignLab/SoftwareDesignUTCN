package org.example.repository.security;

import org.example.model.User;

import java.sql.Connection;
import java.util.List;

public class UserRepositorySQL implements UserRepository {

  private final Connection connection;

  public UserRepositorySQL(Connection connection) {
    this.connection = connection;
  }


  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public User findByUsernameAndPassword(String username, String password) {
    return null;
  }

  @Override
  public boolean create(User user) {
    return false;
  }

  @Override
  public void removeAll() {

  }
}
