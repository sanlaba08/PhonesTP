package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PhoneLineController {
    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    public void addPhoneLine(PhoneLineByUserDto phoneLine){
        phoneLineService.addPhoneLine(phoneLine);
    }

    public void deletePhoneLine(Integer idLine) {
        phoneLineService.deletePhoneLine(idLine);
    }

    public void enablePhoneLine(Integer idLine) {
        phoneLineService.enablePhoneLine(idLine);
    }
}
