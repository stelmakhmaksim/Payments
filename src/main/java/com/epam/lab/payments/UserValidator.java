package com.epam.lab.payments;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.lab.payments.Constants.EMAIL;
import static com.epam.lab.payments.Constants.PASSWORD;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final AuthorizationService authorizationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, "NotEmpty");
        if (userEntity.getEmail().length() < 6 || userEntity.getEmail().length() > 32) {
            errors.reject(EMAIL, "Size.userForm.email");
        }
        if (authorizationService.findUserByEmail(userEntity.getEmail()) != null) {
            errors.reject(EMAIL, "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, "NotEmpty");
        if (userEntity.getPassword().length() < 8 || userEntity.getPassword().length() > 32) {
            errors.reject(PASSWORD, "Size.userForm.password");
        }
    }
}
