package com.epam.lab.payments.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class OrderDTO {
    private int id;

    private Date date;

    private Time time;

    private int value;

    private String description;

    private BankAccountDTO account;
}
