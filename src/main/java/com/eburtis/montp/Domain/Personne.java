package com.eburtis.montp.Domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenoms")
    private String premons;
    @Column(name="age")
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id", referencedColumnName = "id")
    private Departement departement;

    public Personne (){}

    public Personne(Long id, String nom, String premons, int age,Departement departement) {
        this.id = id;
        this.nom = nom;
        this.premons = premons;
        this.age = age;
        this.departement = departement;
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

    public String getPremons() {
        return premons;
    }

    public void setPremons(String premons) {
        this.premons = premons;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
