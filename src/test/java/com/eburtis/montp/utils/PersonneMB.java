package com.eburtis.montp.utils;

import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Domain.Personne;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonneMB {
    private Long id;
    private String nom;
    private String prenoms;
    private int age;
    private Departement departement;

    public PersonneMB setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonneMB setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public PersonneMB setPrenoms(String prenoms) {
        this.prenoms = prenoms;
        return this;
    }

    public PersonneMB setAge(int age) {
        this.age = age;
        return this;
    }

    public PersonneMB setDepartement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public Personne build(){
        Personne personne = mock(Personne.class);
        when(personne.getId()).thenReturn(this.id);
        when(personne.getNom()).thenReturn(this.nom);
        when(personne.getPremons()).thenReturn(this.prenoms);
        when(personne.getAge()).thenReturn(this.age);
        return personne;
    }
}
