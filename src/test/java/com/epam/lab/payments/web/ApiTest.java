package com.epam.lab.payments.web;

import com.epam.lab.payments.dao.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository; // will be used in next tests

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void usersControllerShouldReturnUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(jsonPath("$.*.id", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.lastName", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.admin", iterableWithSize(greaterThan(1))));
    }

    @Test
    public void cardsControllerShouldReturnCards() throws Exception {
        mockMvc.perform(get("/api/cards"))
                .andExpect(jsonPath("$.*.id", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.cardholderName", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.expiration", iterableWithSize(greaterThan(1))));
    }

    @Test
    public void accountsControllerShouldReturnAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
                .andExpect(jsonPath("$.*.id", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.balance", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.blocked", iterableWithSize(greaterThan(1))));
    }

    @Test
    public void ordersControllerShouldReturnOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(jsonPath("$.*.id", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.value", iterableWithSize(greaterThan(1))))
                .andExpect(jsonPath("$.*.description", iterableWithSize(greaterThan(1))));
    }
}
