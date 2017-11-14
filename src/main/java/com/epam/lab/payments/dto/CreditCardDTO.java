package com.epam.lab.payments.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CreditCardDTO {
    private int id;

    private String cardholderName;

    private Date expiration;

    private int securityCode;
}
