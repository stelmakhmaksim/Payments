package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrdersController {

    @RequestMapping("/orders/{id}")
    public String orders(@PathVariable("id") String id, Model model) {

        model.addAttribute("accountId", id);

        return ("madeOrders");
    }
}
