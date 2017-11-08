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
import java.sql.Time;

@Entity
@Table(name = "order", schema = "public", catalog = "srv105242_pay2")
@EqualsAndHashCode
public class OrderEntity {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;
    @Basic
    @Column(name = "date", nullable = false)
    @Getter
    @Setter
    private Date date;
    @Basic
    @Column(name = "time", nullable = false)
    @Getter
    @Setter
    private Time time;
    @Basic
    @Column(name = "value", nullable = false)
    @Getter
    @Setter
    private int value;
    @Basic
    @Column(name = "description", nullable = false, length = -1)
    @Getter
    @Setter
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @Getter
    @Setter
    private BankAccountEntity bankAccountByAccountId;
}
