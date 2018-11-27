package com.epam.lab.payments.dto;

import lombok.Data;

@Data
public class PaymentDTO {
  private int cardNumber;

  private int price;

  private String service;

}
