package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer> {
    Optional<CreditCardEntity> findById(Integer id);

    List<CreditCardEntity> findByUserId(Integer userId);

    List<CreditCardEntity> findByAccountId(Integer accountId);
}
