package com.eburtis.montp.Controller;

import com.eburtis.montp.Application.PersonneVo;

import com.eburtis.montp.Service.PersonneService;
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
    @PostMapping("create")
    public PersonneVo creerPersonne(@RequestBody PersonneVo personneVo) {
        return personneService.creerPersonne(personneVo);
    }
    @GetMapping("getAll")
    public List<PersonneVo> listePersonnes() {
        return personneService.listePersonnes();
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<PersonneVo> findById(@PathVariable Long id) {
        Optional<PersonneVo> personneOptional = personneService.obtenirPersonne(id);
        return personneOptional.map(personneVo -> new ResponseEntity<>(personneVo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("update/{id}")
    public PersonneVo modifierPersonne(@PathVariable Long id, @RequestBody PersonneVo personneVo) {
        PersonneVo updatedPersonne = personneService.modifierPersonne(id, personneVo);
        return updatedPersonne;
    }
    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
         personneService.supprimerPersonne(id);
    }
}
