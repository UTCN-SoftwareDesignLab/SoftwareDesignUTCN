package org.example.model.validation;

import org.example.model.security.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {

  private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
  public static final int MIN_PASSWORD_LENGTH = 8;

  private final User user;
  private final List<String> errors;


  public UserValidator(User user) {
    errors = new ArrayList<>();
    this.user = user;
  }

  public boolean validate() {
    validateUsername(this.user.getUsername());
    validatePassword(this.user.getPassword());
    return this.errors.isEmpty();
  }

  private void validateUsername(String username) {
    if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
      errors.add("Invalid email!");
    }
  }

  private void validatePassword(String password) {
    if (password.length() < MIN_PASSWORD_LENGTH) {
      errors.add("Password too short!");
    }
    if (!containsSpecialCharacter(password)) {
      errors.add("Password must contain a special character!");
    }
    if (!containsDigit(password)) {
      errors.add("Password must contain a digit!");
    }
  }

  private boolean containsSpecialCharacter(String password) {
    return password.matches(".*[^a-zA-Z0-9 ].*");
  }

  private boolean containsDigit(String password) {
    return password.matches(".*\\d.*");
  }

  public List<String> getErrors() {
    return errors;
  }
}
