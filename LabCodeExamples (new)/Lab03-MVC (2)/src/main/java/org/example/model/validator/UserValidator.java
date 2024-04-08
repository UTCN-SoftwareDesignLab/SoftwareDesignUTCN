package org.example.model.validator;

import org.example.model.security.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

  public static final int MIN_PASSWORD_LENGTH = 6;
  public static final String VALID_EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
  public static final String AT_LEAST_ONE_SYMBOL_REGEX = ".*[^a-zA-Z0-9 ].*";
  public static final String AT_LEAST_ONE_UPPERCASE_REGEX = ".*[A-Z].*";
  public static final String AT_LEAST_ONE_DIGIT_REGEX = ".*\\d.*";
  private final User user;
  private final List<String> errors;


  public UserValidator(User user) {
    this.user = user;
    this.errors = new ArrayList<>();
  }

  public boolean validate() {
    validateUsername(this.user.getUsername());
    validatePassword(this.user.getPassword());
    return errors.isEmpty();
  }

  private void validateUsername(String username) {
    if (!username.matches(VALID_EMAIL_REGEX)) {
      errors.add("Invalid email!");
    }
  }

  private void validatePassword(String password) {
    // minimum 6 characters
    if (password.length() < MIN_PASSWORD_LENGTH) {
      errors.add("Password too short!");
    }
    // contains at least one symbol
    if (!password.matches(AT_LEAST_ONE_SYMBOL_REGEX)) {
      errors.add("Password must contain a special character!");
    }
    // contains at least one uppercase letter
    if (!password.matches(AT_LEAST_ONE_UPPERCASE_REGEX)) {
      errors.add("Password must contain an uppercase letter!");
    }
    // contains at least one digit
    if (!password.matches(AT_LEAST_ONE_DIGIT_REGEX)) {
      errors.add("Password must contain a digit!");
    }
  }

  public List<String> getErrors() {
    return this.errors;
  }
}
