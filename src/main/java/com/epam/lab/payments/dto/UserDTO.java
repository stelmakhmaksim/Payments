package com.epam.lab.payments.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private boolean isAdmin;
}


