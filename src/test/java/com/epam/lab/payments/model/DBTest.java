package com.epam.lab.payments.model;

import com.epam.lab.payments.Payments;
import com.epam.lab.payments.dao.UserEntityRepository;
import com.epam.lab.payments.domain.UserEntity;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Aleksandr_Goloshchap on 11/7/2017.
 */
public class DBTest {

    //@Autowired
    //private UserEntityRepository userEntityRepository;

    @Test
    public void findAnyoneUser() {
        //UserEntity foundEntity = userEntityRepository.findAll().stream().findFirst().orElse(null);

        //assertNotNull(foundEntity);
    }
}
