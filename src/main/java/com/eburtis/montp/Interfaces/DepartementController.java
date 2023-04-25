package com.eburtis.montp.Interfaces;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Infrastructure.DepartementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    @Autowired
    private DepartementService departementService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void configureObjectMapper() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @GetMapping
    public ResponseEntity<List<DepartementVo>> findAll(){
        List<DepartementVo> departement = departementService.findAll();
        return new ResponseEntity<>(departement, HttpStatus.OK);
    }
}
