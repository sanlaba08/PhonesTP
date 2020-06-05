package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.model.Tariff;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    public List<TariffProjection> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    public List<TariffProjection> getTariffById(Integer idTariff){
        return tariffService.getTariffById(idTariff);
    }

    public List<TariffProjection> getTariffByName(String originCityName, String destinationCityName) {
        return tariffService.getTariffByName(originCityName,destinationCityName);
    }

//
//    public List<TariffProjection> getTariffByOrigin(String originCityName) {
//        return tariffService.getTariffByOrigin(originCityName);
//    }
//
//    public List<TariffProjection> getTariffByDestination(String destinationCityName) {
//        return tariffService.getTariffByDestination(destinationCityName);
//    }

    public void addClient(TariffDto tariffDto) {
        tariffService.addClient(tariffDto);
    }
}
