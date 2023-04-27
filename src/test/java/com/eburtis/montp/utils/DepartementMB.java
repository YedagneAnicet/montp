package com.eburtis.montp.utils;


import com.eburtis.montp.Domain.Departement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartementMB {

    private Long id;
    private String code;
    private String designation;

    public DepartementMB setId(Long id) {
        this.id = id;
        return this;
    }

    public DepartementMB setCode(String code) {
        this.code = code;
        return this;
    }

    public DepartementMB setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public Departement bluid(){
        Departement departement = mock(Departement.class);
        when(departement.getId()).thenReturn(this.id);
        when(departement.getCode()).thenReturn(this.code);
        when(departement.getDesignation()).thenReturn(this.designation);
        return departement;
    }
}
