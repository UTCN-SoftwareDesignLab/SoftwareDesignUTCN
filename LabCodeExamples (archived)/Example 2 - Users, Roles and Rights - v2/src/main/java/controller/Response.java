package controller;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

  private T data;
  private List<String> errors;

  public Response(T data) {
    this.data = data;
  }

  public Response(List<String> errors) {
    this.errors = errors;
  }

  public Response(String error) {
    this.errors = new ArrayList<>();
    this.errors.add(error);
  }

  public T getData() {
    return data;
  }

  public boolean hasErrors() {
    return errors != null;
  }

  public String getFormattedErrors() {
    return String.join("\n", errors);
  }
}
