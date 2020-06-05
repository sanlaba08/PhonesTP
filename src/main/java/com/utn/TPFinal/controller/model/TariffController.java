package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    public TariffProjection getTariffById(Integer idTariff){
        return tariffService.getTariffById(idTariff);
    }
}
