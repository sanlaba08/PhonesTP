package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.PhoneLineException;
import com.utn.TPFinal.exceptions.TariffNotExistException;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
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
        if (tariffs.size() > 0) {
            return ResponseEntity.ok().body(tariffs);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{idTariff}")
    public ResponseEntity getTariffById(@PathVariable Integer idTariff) {
        ResponseEntity responseEntity = ResponseEntity.ok(tariffController.getTariffById(idTariff));
        if (responseEntity == null) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @GetMapping("/city")
    public ResponseEntity getTariffByName(@RequestParam String originCityName, @RequestParam String destinationCityName) throws TariffNotExistException {
        ResponseEntity responseEntity = ResponseEntity.ok(tariffController.getTariffByName(originCityName, destinationCityName));
        if (responseEntity == null) {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity addTariff(@RequestBody TariffDto tariffDto) throws PhoneLineException {
        try {
            tariffController.addTariff(tariffDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();//uri
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

}
