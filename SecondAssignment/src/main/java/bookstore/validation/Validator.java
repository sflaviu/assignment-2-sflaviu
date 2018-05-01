package bookstore.validation;

import java.util.List;

public interface Validator {
    boolean validate();
    List<String> getErrors();
}
