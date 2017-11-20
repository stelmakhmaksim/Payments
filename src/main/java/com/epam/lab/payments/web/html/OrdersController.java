package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import com.epam.lab.payments.web.Roles;
import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrdersController {
    private final UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/orders/{id}")
    public ModelAndView orders(@PathVariable("id") String id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        UserEntity principalEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
        modelAndView.addObject("firstName", principalEntity.getFirstName());
        modelAndView.addObject("lastName", principalEntity.getLastName());

        modelAndView.addObject("accountId", id);
        modelAndView.addObject("accountNumber", CardNumberAdjuster.valueOf16Digits(id));

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ADMIN.toString()));
        modelAndView.addObject("isAdmin", isAdmin ? true : false);

        modelAndView.setViewName("reports/madeOrders");
        return modelAndView;
    }
}
