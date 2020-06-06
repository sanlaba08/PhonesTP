package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addPhoneLine(@RequestBody PhoneLineByUserDto phoneLine){
        phoneLineController.addPhoneLine(phoneLine);
        return ResponseEntity.ok().build();
    }

    // Baja de Linea con su respectivo usuario.
    @DeleteMapping("/{idLine}")
    public ResponseEntity deletePhoneLine(@PathVariable Integer idLine){
        phoneLineController.deletePhoneLine(idLine);
        return ResponseEntity.ok().build();
    }

    // Activar una Linea con su respectivo usuario.
    @PostMapping("/{idLine}")
    public ResponseEntity enablePhoneLine(@PathVariable Integer idLine){
        phoneLineController.enablePhoneLine(idLine);
        return ResponseEntity.ok().build();
    }
}
