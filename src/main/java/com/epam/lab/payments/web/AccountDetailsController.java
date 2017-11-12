package com.epam.lab.payments.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountDetailsController {

    @RequestMapping("/account")
    public String cards(Model model) {
        model.addAttribute("AccountDetails", "Account details:");
        return "accountDetails";
    }
}
