package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    @Query(value = "select ba.id, ba.owner_name, ba.balance, ba.is_blocked " +
            "    from bank_account as ba " +
            "    inner join credit_card as cc on ba.id = cc.account_id " +
            "    inner join user as u on cc.user_id = u.id " +
            "        and u.id = :id",
            nativeQuery = true)
    List<BankAccountEntity> findByUserId(@Param("id") Integer userId);
}
