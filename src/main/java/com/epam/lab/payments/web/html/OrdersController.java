package com.epam.lab.payments.web.html;

import com.epam.lab.payments.web.html.cosmetic.CardNumberAdjuster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class OrdersController {

    @RequestMapping("/orders/{id}")
    public ModelAndView orders(@PathVariable("id") String id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = request.getUserPrincipal();
        modelAndView.addObject("principal", principal.getName());
        modelAndView.addObject("accountId", id);
        modelAndView.addObject("accountNumber", CardNumberAdjuster.valueOf16Digits(id));
        modelAndView.setViewName("reports/madeOrders");
        return modelAndView;
    }
}
