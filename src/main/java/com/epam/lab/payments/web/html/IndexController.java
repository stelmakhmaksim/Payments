package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping("/admin/panel")
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("user", principal.getName());
        modelAndView.setViewName("reports/admin");
        return modelAndView;
    }
}
