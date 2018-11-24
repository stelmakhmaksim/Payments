package com.epam.lab.payments;

import com.epam.lab.payments.dao.UserRepository;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.lab.payments.Constants.EMAIL;
import static com.epam.lab.payments.Constants.PASSWORD;

@Component
@RequiredArgsConstructor
@Log4j
public class UserValidator implements Validator {
    private final AuthorizationService authorizationService;
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, "NotEmpty");

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(userDTO.getEmail());
        if(!matcher.matches()){
            errors.reject("", "Incorrect email format.");
        }

        /*if (userDTO.getEmail().length() < 4 || userDTO.getEmail().length() > 64) {
            errors.reject(EMAIL, "Size.userForm.email");
            log.info("User email " + userDTO.getEmail() + " has incorrect length"
                    + userDTO.getEmail().length());
        }*/

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            errors.reject("", "Someone already has that email.");
            log.info("User email " + userDTO.getEmail() + " has duplicate by "
                    + userRepository.findByEmail(userDTO.getEmail()));
        }



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD, "NotEmpty");
        Pattern pattern1 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}$");
        Matcher matcher1 = pattern1.matcher(userDTO.getPassword());
        if(!matcher1.matches()){
            errors.reject("", "Your password must be at least 8 characters in length and contain an uppercase letter, a lower case letter, at least one number and one special character (i.e. !, $, #, *).");
        }
        /*if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 32) {
            errors.reject(PASSWORD, "Size.userForm.password");
            log.info("User password has incorrect length");
        }*/

        Pattern pattern2 = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher2 = pattern2.matcher(userDTO.getPhone());
        if(!matcher2.matches()){
            errors.reject("", "Incorrect phone number format!");
        }
    }
}
