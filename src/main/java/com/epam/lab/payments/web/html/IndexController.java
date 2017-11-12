package com.epam.lab.payments.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(@RequestParam(value="UsersTitle", required=false, defaultValue="Users") String name, Model model) {
        model.addAttribute("UsersTitle", "Users:");
        return "index";
    }
}
