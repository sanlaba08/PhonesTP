package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice/tariff")
public class TariffBackController {
    private final TariffController tariffController;

    @Autowired
    public TariffBackController(TariffController tariffController) {
        this.tariffController = tariffController;
    }

    @GetMapping("/{idTariff}")
    public ResponseEntity getTariffById(@PathVariable Integer idTariff){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffController.getTariffById(idTariff));
    }
}
