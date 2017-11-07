package com.epam.lab.payments.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    private final CardRepository cardRepository;

    public IndexController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        Map<String, String> model = new HashMap<>();
        model.put("name", "Cards:");

        Card card = new Card();
        card.cardHolderName = "IVANOV STEPAN";
        card.securityCode = 777;
        card.accountId = new Long(12345678);
        cardRepository.save(card);

        return new ModelAndView("index", model);
    }
}
