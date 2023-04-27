package com.eburtis.montp.Controller;

import com.eburtis.montp.Application.DepartementVo;
import com.eburtis.montp.Service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    @Autowired
    private DepartementService departementService;
    @GetMapping("getAll")
    public List<DepartementVo> listeDepartement(){
        return departementService.listeDepartement();
    }
}
