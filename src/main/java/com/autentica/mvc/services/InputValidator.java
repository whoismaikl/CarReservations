package com.autentica.mvc.services;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by mkl on 3/9/2017.
 */
public class InputValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateReservedFrom", "label.validate.dateFromEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeReservedFrom", "label.validate.timeFromEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateReservedTill", "label.validate.dateTillEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeReservedTill", "label.validate.timeTillEmpty");

    }
}
