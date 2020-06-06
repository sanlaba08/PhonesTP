package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.TariffNotExistException;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backoffice/tariff")
public class TariffBackController {
    private final TariffController tariffController;

    @Autowired
    public TariffBackController(TariffController tariffController) {
        this.tariffController = tariffController;
    }

    @GetMapping("/")
    public ResponseEntity<List<TariffProjection>> getAllTariffs() throws TariffNotExistException {
        List<TariffProjection> tariffs = tariffController.getAllTariffs();
        if (tariffs.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
        }else{
            throw new TariffNotExistException("There are no tariffs loaded yet");
        }
    }

    @GetMapping("/{idTariff}")
    public ResponseEntity<List<TariffProjection>> getTariffById(@PathVariable Integer idTariff) throws TariffNotExistException {
        ResponseEntity responseEntity;
        try{
            responseEntity = ResponseEntity.ok(tariffController.getTariffById(idTariff));
        } catch (TariffNotExistException e){
            responseEntity = ResponseEntity.badRequest().build();
            throw new TariffNotExistException("Incorrect id tariff");
        }
        return responseEntity;
    }

    @GetMapping("/name/")
    public ResponseEntity getTariffByName(@RequestParam String originCityName,@RequestParam String destinationCityName) throws TariffNotExistException {
        ResponseEntity responseEntity;
        try{
            responseEntity = ResponseEntity.ok(tariffController.getTariffByName(originCityName,destinationCityName));
        } catch (TariffNotExistException e){
            responseEntity = ResponseEntity.badRequest().build();
            throw new TariffNotExistException("Incorrect city names");
        }
        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity addTariff(@RequestBody TariffDto tariffDto) {
        tariffController.addTariff(tariffDto);
        return ResponseEntity.ok().build();
    }

}
