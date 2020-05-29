package com.utn.TPFinal.controller;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneline")
public class PhoneLineController {
    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    /* 3) Alta y baja de Lineas (Controller)*/
    // Alta de Linea con su respectivo usuario.
    @PostMapping("/")
    public void addPhoneLine(@RequestBody PhoneLineByUserDto phoneLine){
        phoneLineService.addPhoneLine(phoneLine);
    }

}
