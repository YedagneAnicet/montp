package com.eburtis.montp.utils;

import com.eburtis.montp.Application.DepartementVo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartementVoMB {

    private Long id;
    private String code;
    private String designation;

    public DepartementVoMB setId(Long id) {
        this.id = id;
        return this;
    }

    public DepartementVoMB setCode(String code) {
        this.code = code;
        return this;
    }

    public DepartementVoMB setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public DepartementVo bluid(){
        DepartementVo departementVo = mock(DepartementVo.class);
        when(departementVo.getId()).thenReturn(this.id);
        when(departementVo.getCode()).thenReturn(this.code);
        when(departementVo.getDesignation()).thenReturn(this.designation);
        return departementVo;
    }

}
