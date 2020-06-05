package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backoffice/phoneline")
public class PhoneLineBackController {
    private final PhoneLineController phoneLineController;

    @Autowired
    public PhoneLineBackController(PhoneLineController phoneLineController) {
        this.phoneLineController = phoneLineController;
    }

    /* 3) Alta y baja de Lineas (Controller)*/
    // Alta de Linea con su respectivo usuario.
    @PostMapping("/")
    public void addPhoneLine(@RequestBody PhoneLineByUserDto phoneLine){
        phoneLineController.addPhoneLine(phoneLine);

    }

    // Baja de Linea con su respectivo usuario.
    @DeleteMapping("/{idLine}")
    public void deletePhoneLine(@PathVariable Integer idLine){
        phoneLineController.deletePhoneLine(idLine);
    }

    // Activar una Linea con su respectivo usuario.
    @PostMapping("/{idLine}")
    public void enablePhoneLine(@PathVariable Integer idLine){
        phoneLineController.enablePhoneLine(idLine);
    }
}
