package com.eburtis.montp.controller;

import com.eburtis.montp.Domain.PersonneRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("PersonneControllerTest")
@DisplayName("test unitaire pour le controller de personne")
public class PersonneControllerTest {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private MockMvc mvc;

    private JSONObject json;
    @BeforeAll
    @AfterAll
    public void clearDataBase(){
        this.personneRepository.deleteAll();
        json=null;
    }
    @Test
    @Order(value = 1)
    @DisplayName("test pour la presentation")
    public void testCreatePersonne() throws Exception{
        this.mvc.perform(
                MockMvcRequestBuilders
                        .post("localhost:8080/personnes")
                        .content("{\n" +
                                "   \"nom\":\"John\",\n" +
                                "   \"prenoms\":\"Doe\",\n" +
                                "   \"age\":age,\n" +
                                "   \"departement\":{\n" +
                                "      \"id\":1\n" +
                                "   }\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", is("John")))
                .andExpect(jsonPath("$.prenoms", is("Doe")))
                .andExpect(jsonPath("$.age", is("age")))
                .andExpect(jsonPath("$.departement.id", is("1")))
                .andReturn();

    }
}
