package com.company.restaulator.validators;

import com.company.restaulator.models.dtos.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<ValidPassword, Object> {

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if(obj instanceof UserRegisterDTO) {
            UserRegisterDTO regUser = (UserRegisterDTO) obj;
            if(regUser.getPassword().isEmpty()) {
                return false;
            }
            if(regUser.getConfirmPassword().isEmpty()) {
                return false;
            }
            return regUser.getPassword().equals(regUser.getConfirmPassword());
        }
        return false;
    }
}