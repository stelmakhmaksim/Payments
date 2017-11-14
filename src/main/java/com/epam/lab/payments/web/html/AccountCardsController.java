package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;

@Controller
public class AccountCardsController {

    @RequestMapping("/cards/{id}")
    public String cards(@PathVariable("id") String id, Model model) {
        model.addAttribute("accountId", id);
        model.addAttribute("accountNumber", CardNumberAdjuster.valueOf16Digits(id));
        return "reports/accountCards";
    }
}
