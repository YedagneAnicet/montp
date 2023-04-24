package com.eburtis.montp.Interfaces;

import com.eburtis.montp.Application.PersonneVo;

import com.eburtis.montp.Infrastructure.PersonneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void configureObjectMapper() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @PostMapping
    public ResponseEntity<PersonneVo> create(@RequestBody PersonneVo personneVo) {
        PersonneVo createdPersonne = personneService.create(personneVo);
        return new ResponseEntity<>(createdPersonne, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonneVo>> findAll() {
        List<PersonneVo> personnes = personneService.findAll();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonneVo> findById(@PathVariable Long id) {
        Optional<PersonneVo> personneOptional = personneService.findById(id);
        return personneOptional.map(personneVo -> new ResponseEntity<>(personneVo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonneVo> update(@PathVariable Long id, @RequestBody PersonneVo personneVo) {
        PersonneVo updatedPersonne = personneService.update(id, personneVo);
        if (updatedPersonne != null) {
            return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        personneService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
