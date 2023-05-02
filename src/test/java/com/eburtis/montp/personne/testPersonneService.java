package com.eburtis.montp.personne;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Application.PersonneVo;
import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Domain.Personne;
import com.eburtis.montp.Repository.DepartementRepository;
import com.eburtis.montp.Repository.PersonneRepository;
import com.eburtis.montp.Service.PersonneService;
import com.eburtis.montp.utils.DepartementMB;
import com.eburtis.montp.utils.DepartementVoMB;
import com.eburtis.montp.utils.PersonneMB;
import com.eburtis.montp.utils.PersonneVoMB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.util.Lazy.empty;

@ExtendWith(MockitoExtension.class)
class TestPersonneService {
    @Mock
    private PersonneRepository personneRepository;
    @Mock
    private DepartementRepository departementRepository;
    @InjectMocks
    private PersonneService personneService;

    Long personneId = 1L;
    Long departementId = 2L;
    Departement departement = new DepartementMB()
            .setId(departementId)
            .setCode("DAB")
            .setDesignation("Dabou")
            .bluid();
    DepartementVo departementVo = new DepartementVoMB()
            .setId(departementId)
            .setCode("DAB")
            .setDesignation("Dabou")
            .bluid();
    Personne yedagne = new PersonneMB()
            .setId(personneId)
            .setNom("Yedagne")
            .setPrenoms("Anicet")
            .setAge(22)
            .setDepartement(departement)
            .build();
    PersonneVo yedagneVo = new PersonneVoMB()
            .setId(personneId)
            .setNom("Yedagne")
            .setPrenoms("Anicet")
            .setAge(22)
            .setDepartement(departementVo)
            .build();
    Personne bamba = new PersonneMB()
            .setId(2L)
            .setNom("Bamba")
            .setPrenoms("Wingnemila")
            .setAge(24)
            .setDepartement(departement)
            .build();

    @Test
    @DisplayName("test unitaire de la methode listePersonnes du service personne")
    void testListePersonnes(){
        //GIVEN
        List<Personne> personnes = new ArrayList<>();
        personnes.add(yedagne);
        personnes.add(bamba);

        when(personneRepository.findAll()).thenReturn(personnes);

        //WHEN
        List<PersonneVo> personnesVo = personneService.listePersonnes();

        // THEN
        verify(personneRepository).findAll();
        assertEquals(personnes.size(), personnesVo.size());
        assertNotNull(personnesVo);
    }
    @Test
    @DisplayName("test unitaire de la methode obtenirPersonne de personneService")
    void testObtenirPersonne(){
        //GIVEN
        when(personneRepository.findById(personneId)).thenReturn(Optional.of(yedagne));

        //WHEN
        Optional<PersonneVo> personneVo = personneService.obtenirPersonne(personneId);

        //THEN
        verify(personneRepository).findById(personneId);
        assertNotNull(personneVo);
        assertTrue(personneVo.isPresent());
    }
    @Test
    @DisplayName("test unitaire de la methode de creation d'une personne de la couche personneService")
    void testCreerPersonne(){
        //GIVEN
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));
        when(personneRepository.save(Mockito.any(Personne.class))).thenReturn(yedagne);

        //When
        PersonneVo savePersonne = personneService.creerPersonne(yedagneVo);

        //Then
        assertNotNull(savePersonne);
        assertEquals(savePersonne.getNom(), yedagneVo.getNom());
    }
    @Test
    @DisplayName("test unitaire de la methode modifierPersonne de la couche service Personne")
    void testModifierPersonne(){
        //Given
        when(personneRepository.findById(personneId)).thenReturn(Optional.of(yedagne));
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));
        when(personneRepository.save(Mockito.any(Personne.class))).thenReturn(yedagne);

        //When
        PersonneVo updatePersonne = personneService.modifierPersonne(personneId,yedagneVo);

        //Then
        assertNotNull(updatePersonne);
    }
    @Test
    @DisplayName("test unitaire de la methode de suppression d'une personne de la couche personneservice")
    void testSupprimerPersonne(){
        //Given
        doNothing().when(personneRepository).deleteById(personneId);


        //When
        personneService.supprimerPersonne(personneId);

        //Then
        verify(personneRepository).deleteById(personneId);
    }
}
