package com.epam.lab.payments.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "credit_card", schema = "public", catalog = "srv105242_pay2")
@EqualsAndHashCode
public class CreditCardEntity {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;
    @Basic
    @Column(name = "cardholder_name", nullable = false, length = -1)
    @Getter
    @Setter
    private String cardholderName;
    @Basic
    @Column(name = "expiration", nullable = false)
    @Getter
    @Setter
    private Date expiration;
    @Basic
    @Column(name = "security_code", nullable = false)
    @Getter
    @Setter
    private int securityCode;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @Getter
    @Setter
    private BankAccountEntity bankAccountByAccountId;
}