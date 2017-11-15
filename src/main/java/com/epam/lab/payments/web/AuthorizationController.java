package com.epam.lab.payments.web;

import com.epam.lab.payments.UserValidator;
import com.epam.lab.payments.dto.UserDTO;
import com.epam.lab.payments.services.AuthorizationService;
import com.epam.lab.payments.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

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
        UserDTO user = new UserDTO();
        modelAndView.addObject("user", user);
        modelAndView.setViewName(REGISTRATION);
        return modelAndView;
    }

    @RequestMapping(value = REGISTRATION, method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserDTO user, BindingResult bindingResult) {
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
            modelAndView.addObject("user", new UserDTO());
            modelAndView.setViewName(REGISTRATION);
        }
        return modelAndView;
    }

    @RequestMapping(value={"/", LOGIN, REGISTRATION}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            modelAndView.setViewName("reports/userDetails");
        } else {
            modelAndView.setViewName(LOGIN);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ModelAndView updateUser(UserDTO user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Principal principal = request.getUserPrincipal();
        if (user.getEmail().equals(principal.getName())) {
            authorizationService.update(user);
        }
        modelAndView.setViewName("reports/userDetails");
        return modelAndView;
    }

}
