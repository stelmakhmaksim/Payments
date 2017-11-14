package com.epam.lab.payments.web.html;

import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.epam.lab.payments.Constants.ROLE_ADMIN;
import static com.epam.lab.payments.Constants.ROLE_USER;


@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request, Model model) {
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

        if (isAdmin) {
            return "reports/admin";
        } else if (isUser) {
            model.addAttribute("accountId", "1234567890");
            model.addAttribute("accountNumber", CardNumberAdjuster.valueOf16Digits("1234567890"));

            return "reports/accountDetails";
        } else {
            model.addAttribute("error", "Authorization Error");
            return "error";
        }
    }
}
