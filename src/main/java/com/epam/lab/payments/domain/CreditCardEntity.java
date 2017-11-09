package com.epam.lab.payments.domain;

import lombok.Data;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "credit_card")
@Data
public class CreditCardEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "cardholder_name", nullable = false, length = -1)
    private String cardholderName;

    @Basic
    @Column(name = "expiration", nullable = false)
    private Date expiration;

    @Basic
    @Column(name = "security_code", nullable = false)
    private int securityCode;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity bankAccountByAccountId;
}