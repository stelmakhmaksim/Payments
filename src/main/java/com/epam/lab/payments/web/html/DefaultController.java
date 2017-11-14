package com.epam.lab.payments.web.html;

import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.epam.lab.payments.Constants.ROLE_ADMIN;


@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request, Model model) {
        if (request.isUserInRole(ROLE_ADMIN)) {
            return "reports/admin";
        }

        model.addAttribute("accountId", "1234567890");
        model.addAttribute("accountNumber", CardNumberAdjuster.valueOf16Digits("1234567890"));

        return "reports/accountDetails";
    }
}