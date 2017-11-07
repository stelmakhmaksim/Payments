package com.epam.lab.payments.model;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Александр on 03.11.2017.
 */
public interface AccountRepository extends Repository<Account, Long> {
//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    List<Account> find(@Param("user_id") Integer user_id);
}
