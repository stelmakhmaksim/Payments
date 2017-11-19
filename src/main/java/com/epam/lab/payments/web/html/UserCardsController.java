package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserCardsController {
    private final UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/userCards/{id}")
    public ModelAndView userCards(@PathVariable("id") String id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        modelAndView.addObject("userId", id);

        UserEntity userEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
        modelAndView.addObject("firstName", userEntity.getFirstName());
        modelAndView.addObject("lastName", userEntity.getLastName());

        modelAndView.setViewName("reports/userCards");
        return modelAndView;
    }
}
