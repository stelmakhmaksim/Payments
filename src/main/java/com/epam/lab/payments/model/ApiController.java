package com.epam.lab.payments.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CardRepository cardRepository;

    public ApiController(CardRepository visitsRepository) {
        this.cardRepository = visitsRepository;
    }

    @GetMapping("/cards")
    public Iterable<Card> getVisits() {
        return cardRepository.findAll();
    }
}
