package model.validation;

import java.util.ArrayList;
import java.util.List;

public class Notification<T> {

    private T result;
    private final List<String> errors = new ArrayList<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String getFormattedErrors() {
        return String.join("\n", errors);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
