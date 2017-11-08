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
@Table(name = "user", schema = "public", catalog = "srv105242_pay2")
@EqualsAndHashCode
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private int id;
    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    @Getter
    @Setter
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = -1)
    @Getter
    @Setter
    private String lastName;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    @Getter
    @Setter
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    @Getter
    @Setter
    private String password;
    @Basic
    @Column(name = "phone", nullable = false, length = -1)
    @Getter
    @Setter
    private String phone;
    @Basic
    @Column(name = "is_admin", nullable = false)
    @Getter
    @Setter
    private boolean isAdmin;
}