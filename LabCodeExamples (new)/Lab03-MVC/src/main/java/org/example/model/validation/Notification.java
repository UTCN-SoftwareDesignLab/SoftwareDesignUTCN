package org.example.model.validation;

import java.util.ArrayList;
import java.util.List;

public class Notification<T> {

  private T result;
  private final List<String> errors;

  public Notification() {
    this.errors = new ArrayList<>();
  }

  public void addError(String message) {
    errors.add(message);
  }

  public boolean hasErrors() {
    return errors.size() > 0;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public T getResult() throws ResultFetchException {
    if (hasErrors()) {
      throw new ResultFetchException(errors);
    }
    return result;
  }

  public String getFormattedErrors() {
    return String.join("\n", errors);
  }
}
