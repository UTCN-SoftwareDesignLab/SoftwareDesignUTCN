package model.validation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 18/03/2017.
 */
public class ResultFetchException extends RuntimeException {
    private final List<String> errors;

    public ResultFetchException(List<String> errors) {
        super("Failed to fetch the result as the operation encountered errors.");
        this.errors = errors;
    }

    @Override
    public String toString() {
        return errors.stream().map(Object::toString).collect(Collectors.joining(",")) + super.toString();
    }
}
