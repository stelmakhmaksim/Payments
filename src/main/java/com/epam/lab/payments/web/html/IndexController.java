package com.epam.lab.payments.web.html;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.epam.lab.payments.Constants.ROLE_ADMIN;

@Controller
public class IndexController {

    @RequestMapping("/admin/panel")
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(ROLE_ADMIN));

        if (isAdmin) {
            modelAndView.addObject("user", principal.getName());
            modelAndView.setViewName("reports/admin");
        } else {
            modelAndView.setViewName("default");
        }
        return modelAndView;

    }
}
