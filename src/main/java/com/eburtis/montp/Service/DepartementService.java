package com.eburtis.montp.Service;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Domain.Departement;
import com.eburtis.montp.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;

    public List<DepartementVo> listeDepartement(){
        List<Departement> departements = departementRepository.findAll();
        return departements.stream()
                .map(departement -> {
                    DepartementVo departementVo = new DepartementVo();
                    departementVo.setId(departement.getId());
                    departementVo.setCode(departement.getCode());
                    departementVo.setDesignation(departement.getDesignation());
                    return departementVo;
                })
                .collect(Collectors.toList());
    }
}
