package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import com.epam.lab.payments.web.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class DefaultController {
    private final UserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public ModelAndView userDetails(HttpServletRequest request) {

        System.out.println(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities());

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ADMIN.toString()));

        boolean isUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.USER.toString()));

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        if(principal==null) {
            modelAndView.addObject("error", "Authorization Error");
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("principal", principal.getName());

        UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
        modelAndView.addObject("firstName", principalEntity.getFirstName());
        modelAndView.addObject("lastName", principalEntity.getLastName());


        if (isAdmin) {
            modelAndView.setViewName("reports/admin");
        } else if (isUser) {
            modelAndView.addObject("id", principalEntity.getId());
            modelAndView.setViewName("reports/userDetails");
        } else {
            modelAndView.addObject("error", "Authorization Error");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
