package com.utn.TPFinal.controller;

import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tariff")
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/{idTariff}")
    public TariffProjection getTariffById(@PathVariable Integer idTariff){
        return tariffService.getTariffById(idTariff);
    }
}
