package com.epam.lab.payments.web.html;

import com.epam.lab.payments.domain.UserEntity;
import com.epam.lab.payments.dto.BankAccountDTO;
import com.epam.lab.payments.services.UserDetailsServiceImpl;
import com.epam.lab.payments.web.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountCardsController {
    private final UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/cards/{id}")
    public ModelAndView accountCards(@PathVariable("id") String accountId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        UserEntity userEntity = userDetailsService.loadUserEntityByUsername(principal.getName());
        modelAndView.addObject("firstName", userEntity.getFirstName());
        modelAndView.addObject("lastName", userEntity.getLastName());

        modelAndView.addObject("accountId", accountId);
        modelAndView.addObject("accountNumber", CardNumberAdjuster.valueOf16Digits(accountId));

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ADMIN.toString()));
        modelAndView.addObject("isAdmin", isAdmin ? true : false);

        modelAndView.setViewName("reports/accountCards");
        return modelAndView;
    }
}
