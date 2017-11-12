package com.epam.lab.payments.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles="user")
public class IndexTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void indexControllerShouldReturnHtmlPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void cardsControllerShouldReturnHtmlPage() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk());
    }

    @Test
    public void accountsControllerShouldReturnHtmlPage() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    public void ordersControllerShouldReturnHtmlPage() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }
}
