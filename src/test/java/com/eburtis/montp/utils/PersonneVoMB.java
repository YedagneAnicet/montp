package com.eburtis.montp.utils;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Application.PersonneVo;
import com.eburtis.montp.Domain.Personne;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonneVoMB {
    private Long id;
    private String nom;
    private String prenoms;
    private Integer age;
    private DepartementVo departement;

    public PersonneVoMB setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonneVoMB setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public PersonneVoMB setPrenoms(String prenoms) {
        this.prenoms = prenoms;
        return this;
    }

    public PersonneVoMB setAge(Integer age) {
        this.age = age;
        return this;
    }

    public PersonneVoMB setDepartement(DepartementVo departement) {
        this.departement = departement;
        return this;
    }

    public PersonneVo build(){
        PersonneVo personneVo = mock(PersonneVo.class);
        when(personneVo.getId()).thenReturn(this.id);
        when(personneVo.getNom()).thenReturn(this.nom);
        when(personneVo.getPrenoms()).thenReturn(this.prenoms);
        when(personneVo.getAge()).thenReturn(this.age);
        when(personneVo.getDepartement()).thenReturn(this.departement);
        return personneVo;
    }
}
