package com.epam.lab.payments.domain;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
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
@Table(name = "order")
@Data
public class OrderEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "date", nullable = false)
    private Date date;

    @Basic
    @Column(name = "time", nullable = false)
    private Time time;

    @Basic
    @Column(name = "value", nullable = false)
    private int value;

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity bankAccountByAccountId;
}