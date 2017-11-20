package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserAccountsController {

    @RequestMapping("/userAccounts/{id}")
    public ModelAndView accounts(@PathVariable("id") String id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        modelAndView.addObject("userId", id);

        modelAndView.setViewName("reports/userAccounts");
        return modelAndView;
    }
}
