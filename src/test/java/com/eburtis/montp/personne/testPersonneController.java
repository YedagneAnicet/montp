package com.eburtis.montp.personne;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Application.PersonneVo;
import com.eburtis.montp.Controller.PersonneController;
import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Domain.Personne;
import com.eburtis.montp.Service.PersonneService;
import com.eburtis.montp.utils.DepartementMB;
import com.eburtis.montp.utils.DepartementVoMB;
import com.eburtis.montp.utils.PersonneMB;
import com.eburtis.montp.utils.PersonneVoMB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = PersonneController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class testPersonneController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonneService personneService;

    @Autowired
    private ObjectMapper objectMapper;


    private Personne personne;
    private PersonneVo personneVo;

    private Departement departement;

    private DepartementVo departementVo;
    @BeforeEach
    public void init (){
        departement = new DepartementMB()
                .setId(1L)
                .setCode("DAB")
                .setDesignation("Dabou")
                .bluid();
        departementVo = new DepartementVoMB()
                .setId(1L)
                .setCode("DAB")
                .setDesignation("Dabou")
                .bluid();
        personne = new PersonneMB()
                .setId(1L)
                .setNom("Yedagne")
                .setPrenoms("Anicet")
                .setAge(22)
                .setDepartement(departement)
                .build();
        personneVo = new PersonneVoMB()
                .setId(1L)
                .setNom("Yedagne")
                .setPrenoms("Anicet")
                .setAge(22)
                .setDepartement(departementVo)
                .build();
    }
    @Test
    public void PersonneController_Create() throws Exception{
        given(personneService.creerPersonne(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

    ResultActions reponse = mockMvc.perform(post("/personnes/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(personneVo)));
    reponse.andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("Yedagne", CoreMatchers.is(personneVo.getNom())))
            .andExpect(MockMvcResultMatchers.jsonPath("Anicet",CoreMatchers.is(personneVo.getPrenoms())));

    }


}
