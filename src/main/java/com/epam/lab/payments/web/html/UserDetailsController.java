package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class UserDetailsController {

    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public ModelAndView userDetails(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", principal.getName());
        modelAndView.setViewName("reports/userDetails");

        return modelAndView;
    }

}
