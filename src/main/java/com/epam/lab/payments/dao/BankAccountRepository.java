package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    @Query(value = "SELECT\n" +
            "  \"ba\".\"id\",\n" +
            "  \"ba\".\"owner_name\",\n" +
            "  \"ba\".\"balance\",\n" +
            "  \"ba\".\"is_blocked\"\n" +
            "FROM \"user\" \"u\"\n" +
            "  INNER JOIN \"credit_card\" \"cc\" ON \"u\".\"id\" = \"user_id\"\n" +
            "  INNER JOIN \"bank_account\" \"ba\" ON \"account_id\" = \"ba\".\"id\"\n" +
            "WHERE \"u\".\"id\" = :id\n" +
            "GROUP BY" +
            "\"ba\".\"id\",\n" +
            "\"ba\".\"owner_name\",\n" +
            "\"ba\".\"balance\",\n" +
            "\"ba\".\"is_blocked\"",
            nativeQuery = true)
    /*@Query("select ba.id, ba.ownerName, ba.balance, ba.isBlocked from CreditCardEntity cc " +
            "inner join cc.account ba inner join cc.user u where u.id = :id")*/
    /*@Query("select ba.id, ba.ownerName, ba.balance, ba.isBlocked from CreditCardEntity cc " +
            "inner join cc.account as ba where cc.user = :id")*/
    /*@Query("select ba.id,ba.ownerName, ba.balance, ba.isBlocked from BankAccountEntity ba," +
           "CreditCardEntity c,  UserEntity u where u.id =:id and ba.id = " +
           "c.account and c.user = u.id")*/
    List<BankAccountEntity> findByUserId(@Param("id") Integer userId);
}
