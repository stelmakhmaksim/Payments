package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.epam.lab.payments.Constants.ROLE_ADMIN;
import static com.epam.lab.payments.Constants.ROLE_USER;


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
                .contains(new SimpleGrantedAuthority(ROLE_ADMIN));

        boolean isUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(ROLE_USER));

        ModelAndView modelAndView = new ModelAndView();

        if (isAdmin) {
            Principal principal = request.getUserPrincipal();
            modelAndView.addObject("user", principal.getName());

            modelAndView.setViewName("reports/admin");
        } else if (isUser){
            Principal principal = request.getUserPrincipal();
            modelAndView.addObject("user", principal.getName());

            UserEntity userEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
            modelAndView.addObject("id", userEntity.getId());
            modelAndView.addObject("firstName", userEntity.getFirstName());
            modelAndView.addObject("lastName", userEntity.getLastName());

            modelAndView.setViewName("reports/userDetails");
        } else {
            modelAndView.addObject("error", "Authorization Error");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
