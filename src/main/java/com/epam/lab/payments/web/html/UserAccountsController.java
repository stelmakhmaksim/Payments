package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountsController {

    @RequestMapping("/userAccounts/{id}")
    public String accounts(@PathVariable("id") String id, Model model) {
        model.addAttribute("userId", id);
        return "reports/userAccounts";
    }
}
