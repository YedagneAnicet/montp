package com.eburtis.montp.Application;

import com.eburtis.montp.Domain.Departement;

/**
 * @author anicet yedagne https://github.com/YedagneAnicet
 *
 * cette classe est reprensente le VO de la Departement
 *
 * */
public class DepartementVo {
    private Long id;
    private String code;
    private String designation;

    public DepartementVo() {}

    public DepartementVo(Departement departement) {
        this.id = departement.getId();
        this.code = departement.getCode();
        this.designation = departement.getDesignation();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
