package com.epam.lab.payments.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Александр on 03.11.2017.
 */

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean isBlocked;
}
