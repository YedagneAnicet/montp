package com.eburtis.montp.personne;

import com.eburtis.montp.Application.PersonneVo;
import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Domain.Personne;
import com.eburtis.montp.Repository.PersonneRepository;
import com.eburtis.montp.Service.PersonneService;
import com.eburtis.montp.utils.DepartementMB;
import com.eburtis.montp.utils.PersonneMB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class testPersonneService {
    @Mock
    private PersonneRepository personneRepository;
    @InjectMocks
    private PersonneService personneService;
    @Test
    @DisplayName("test unitaire de la methode listePersonnes du service personne")
    public void testListePersonnes(){
        //GIVEN
        List<Personne> personnes = new ArrayList<>();
        Departement departement = new DepartementMB()
                .setId(1L)
                .setCode("DAB")
                .setDesignation("Dabou")
                .bluid();
        Personne personne1 = new PersonneMB()
                .setId(1l)
                .setNom("Yedagne")
                .setPrenoms("Anicet")
                .setAge(22)
                .setDepartement(departement)
                .build();
        Personne personne2 = new PersonneMB()
                .setId(2L)
                .setNom("Bamba")
                .setPrenoms("Wingnemila")
                .setAge(24)
                .setDepartement(departement)
                .build();
        personnes.add(personne1);
        personnes.add(personne2);

        when(personneRepository.findAll()).thenReturn(personnes);

        //WHEN
        List<PersonneVo> personnesVo = personneService.listePersonnes();

        // THEN
        verify(personneRepository).findAll();

        assertEquals(personnes.size(), personnesVo.size());
        assertNotNull(personnesVo);

    }

}
