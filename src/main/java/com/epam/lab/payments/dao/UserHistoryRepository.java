package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistoryEntity, Integer> {
}
