package com.epam.lab.payments.web.html;

import com.epam.lab.payments.dto.BankAccountDTO;
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
public class AccountCardsController {

    @RequestMapping("/cards/{id}")
    public ModelAndView accountCards(@PathVariable("id") String accountId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());

        modelAndView.addObject("accountId", accountId);
        modelAndView.addObject("accountNumber", CardNumberAdjuster.valueOf16Digits(accountId));

        modelAndView.setViewName("reports/accountCards");
        return modelAndView;
    }
}
