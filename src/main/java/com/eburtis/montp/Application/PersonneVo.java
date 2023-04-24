package com.eburtis.montp.Application;

import com.eburtis.montp.Domain.Personne;

public class PersonneVo {
    private Long id;
    private String nom;
    private String prenoms;
    private Integer age;
    private DepartementVo departement;

    public PersonneVo() {}

    public PersonneVo(Personne personne, DepartementVo departementVo) {
        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenoms = personne.getPremons();
        this.age = personne.getAge();
        this.departement = departementVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DepartementVo getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementVo departement) {
        this.departement = departement;
    }
}

