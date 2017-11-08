package com.epam.lab.payments.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "srv105242_pay2")
@EqualsAndHashCode
public class BankAccountEntity {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;
    @Basic
    @Column(name = "owner_name", nullable = false, length = -1)
    @Getter
    @Setter
    private String ownerName;
    @Basic
    @Column(name = "balance", nullable = false)
    @Getter
    @Setter
    private int balance;
    @Basic
    @Column(name = "is_blocked", nullable = false)
    @Getter
    @Setter
    private boolean isBlocked;
}