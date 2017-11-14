package com.epam.lab.payments.dto;

import lombok.Data;

@Data
public class BankAccountDTO {
    private int id;

    private String ownerName;

    private int balance;

    private boolean isBlocked;
}
