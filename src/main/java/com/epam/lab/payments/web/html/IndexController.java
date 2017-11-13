package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/adminPanel")
    public String admin(Model model) {

        model.addAttribute("AccountDetails", "Account details:");

        return "reports/admin";
    }
}
