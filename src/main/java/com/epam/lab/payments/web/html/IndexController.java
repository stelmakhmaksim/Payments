package com.epam.lab.payments.web.html;

import com.epam.lab.payments.web.Roles;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ADMIN.toString()));

        if (isAdmin) {
            modelAndView.addObject("user", principal.getName());
            modelAndView.setViewName("reports/admin");
        } else {
            modelAndView.setViewName("default");
        }
        return modelAndView;

    }
}
