package com.epam.lab.payments.dto;

import lombok.Data;

@Data
public class RefillDTO {
  private int cardNumber;

  private int amount;

}
