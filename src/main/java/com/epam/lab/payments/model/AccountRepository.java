package com.epam.lab.payments.model;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends Repository<Account, Long> {
}
