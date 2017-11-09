package com.epam.lab.payments.domain;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "srv105242_pay2")
@Data
public class BankAccountEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "owner_name", nullable = false, length = -1)
    private String ownerName;

    @Basic
    @Column(name = "balance", nullable = false)
    private int balance;

    @Basic
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;
}