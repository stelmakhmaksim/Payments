package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Aleksandr_Goloshchap on 11/7/2017.
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long userId);
}
