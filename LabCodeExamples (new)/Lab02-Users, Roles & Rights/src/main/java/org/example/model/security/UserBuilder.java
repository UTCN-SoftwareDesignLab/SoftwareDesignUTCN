package org.example.model.security;

import java.util.List;

public class UserBuilder {
  private User user;

  public UserBuilder() {
    user = new User();
  }

  public UserBuilder setUsername(String username) {
    user.setUsername(username);
    return this;
  }

  public UserBuilder setPassword(String password) {
    user.setPassword(password);
    return this;
  }

  public UserBuilder setRoles(List<Role> roles) {
    user.setRoles(roles);
    return this;
  }

  public User build() {
    return user;
  }
}
