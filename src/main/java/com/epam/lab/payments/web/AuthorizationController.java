package com.epam.lab.payments.web;

import com.epam.lab.payments.UserValidator;
import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.AuthorizationService;
import com.epam.lab.payments.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.epam.lab.payments.Constants.LOGIN;
import static com.epam.lab.payments.Constants.REGISTRATION;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final SecurityService securityService;

    @RequestMapping(value = REGISTRATION, method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("user", user);
        modelAndView.setViewName(REGISTRATION);
        return modelAndView;
    }

    @RequestMapping(value = REGISTRATION, method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserEntity user, BindingResult bindingResult) {
        UserValidator userValidator = new UserValidator(authorizationService);
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", user);
            modelAndView.addObject("successMessage", "Some fields has errors");
            modelAndView.setViewName(REGISTRATION);
        } else {
            authorizationService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new UserEntity());
            modelAndView.setViewName(REGISTRATION);
        }
        return modelAndView;
    }

    @RequestMapping(value={"/", LOGIN}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN);
        return modelAndView;
    }

}
