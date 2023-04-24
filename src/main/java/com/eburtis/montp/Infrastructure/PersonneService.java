package com.eburtis.montp.Infrastructure;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Application.PersonneVo;
import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Domain.DepartementRepository;
import com.eburtis.montp.Domain.Personne;
import com.eburtis.montp.Domain.PersonneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private DepartementRepository departementRepository;

    public PersonneVo create(PersonneVo personneVo) {
        if (personneVo.getDepartement() == null || personneVo.getDepartement().getId() == null) {
            throw new IllegalArgumentException("Identifiant du département manquant");
        }
        Departement departement = departementRepository.findById(personneVo.getDepartement().getId())
                .orElseThrow(() -> new IllegalArgumentException("Département introuvable"));
        if (departement == null) {
            throw new IllegalArgumentException("Département invalide");
        }
        Personne personne = new Personne(null, personneVo.getNom(), personneVo.getPrenoms(), personneVo.getAge(), departement);
        Personne savedPersonne = personneRepository.save(personne);
        return new PersonneVo(savedPersonne, new DepartementVo(savedPersonne.getDepartement()));
    }

    public PersonneVo getPersonneByIdWithDepartement(Long id) {
        Personne personne = personneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La personne avec " + id + " est introuvable"));
        Departement departement = departementRepository.findById(personne.getDepartement().getId())
                .orElseThrow(() -> new EntityNotFoundException("Département with id " + personne.getDepartement().getId() + " not found"));
        return new PersonneVo(personne, new DepartementVo(departement));
    }

    public List<PersonneVo> findAll() {
        List<Personne> personnes = personneRepository.findAll();
        return personnes.stream().map(p -> new PersonneVo(p, new DepartementVo(p.getDepartement()))).collect(Collectors.toList());
    }

    public Optional<PersonneVo> findById(Long id) {
        Optional<Personne> personneOptional = personneRepository.findById(id);
        return personneOptional.map(p -> new PersonneVo(p, new DepartementVo(p.getDepartement())));
    }

    public PersonneVo update(Long id, PersonneVo personneVo) {
        Optional<Personne> personneOptional = personneRepository.findById(id);
        if (personneOptional.isPresent()) {
            Personne personne = personneOptional.get();
            Departement departement = departementRepository.findById(personneVo.getDepartement().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Département introuvable"));
            personne.setNom(personneVo.getNom());
            personne.setPremons(personneVo.getPrenoms());
            personne.setAge(personneVo.getAge());
            personne.setDepartement(departement);
            Personne updatedPersonne = personneRepository.save(personne);
            return new PersonneVo(updatedPersonne, new DepartementVo(updatedPersonne.getDepartement()));
        }
        return null;
    }

    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }

}
