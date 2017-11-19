package com.epam.lab.payments.dao;

import com.epam.lab.payments.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query(value = "SELECT DISTINCT \n" +
            "    \"o\".\"id\",\n" +
            "    \"o\".\"date\",\n" +
            "    \"o\".\"time\",\n" +
            "    \"o\".\"value\",\n" +
            "    \"o\".\"description\",\n" +
            "    \"o\".\"account_id\"\n" +
            "FROM \"user\" \"u\"\n" +
            "    INNER JOIN \"credit_card\" \"c\" ON \"u\".\"id\" = \"user_id\"\n" +
            "    INNER JOIN \"bank_account\" \"b\" ON \"c\".\"account_id\" = \"b\".\"id\"\n" +
            "    INNER JOIN \"order\" \"o\" ON \"o\".\"account_id\" = \"b\".\"id\"\n" +
            "WHERE \"u\".\"id\" = :id", nativeQuery = true)
    List<OrderEntity> findByUserId(@Param("id") Integer userId);

    List<OrderEntity> findByAccountId(Integer accountId);
}
