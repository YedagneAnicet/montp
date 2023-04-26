package com.eburtis.montp.Controller;

import com.eburtis.montp.Application.PersonneVo;

import com.eburtis.montp.Service.PersonneService;
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

    @PostMapping("create")
    public PersonneVo create(@RequestBody PersonneVo personneVo) {
        return personneService.create(personneVo);
    }

    @GetMapping("getAll")
    public List<PersonneVo> findAll() {
        return personneService.findAll();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<PersonneVo> findById(@PathVariable Long id) {
        Optional<PersonneVo> personneOptional = personneService.findById(id);
        return personneOptional.map(personneVo -> new ResponseEntity<>(personneVo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("update/{id}")
    public PersonneVo update(@PathVariable Long id, @RequestBody PersonneVo personneVo) {
        PersonneVo updatedPersonne = personneService.update(id, personneVo);
        return updatedPersonne;
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
         personneService.deleteById(id);
    }
}
