package application.booklibrary;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "valid.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "valid.author");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "valid.year");
    }
}
