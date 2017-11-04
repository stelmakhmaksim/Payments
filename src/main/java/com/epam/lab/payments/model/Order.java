package com.epam.lab.payments.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Александр on 03.11.2017.
 */
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime ldt;
    private Integer value;
    private String description;
}
