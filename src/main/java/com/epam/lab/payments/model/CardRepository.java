package com.epam.lab.payments.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Александр on 03.11.2017.
 */
public interface CardRepository extends CrudRepository<Card, Long> {
}
