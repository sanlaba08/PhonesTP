package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.exceptions.BillNotExistException;
import com.utn.TPFinal.exceptions.TariffNotExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/tariff")
public class TariffWebController {
    private final TariffController tariffController;

    @Autowired
    public TariffWebController(TariffController tariffController) {
        this.tariffController = tariffController;
    }


    @GetMapping("/all/")
    public ResponseEntity<List<TariffProjection>> getAllTariffs() throws TariffNotExistException {
        List<TariffProjection> tariffs = tariffController.getAllTariffs();
        if (tariffs.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
        }else{
            throw new TariffNotExistException("There are no tariffs loaded yet");
        }
    }

    @GetMapping("/id/")
    public ResponseEntity<List<TariffProjection>> getTariffById(@RequestParam Integer idTariff) throws TariffNotExistException {
        List<TariffProjection> tariffs = tariffController.getTariffById(idTariff);
        if (tariffs.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
        }else{
            throw new TariffNotExistException("There is no tariff with this id: "+ idTariff);
        }
    }

    @GetMapping("/name/")//no deberia ser list jeje
    public ResponseEntity<List<TariffProjection>> getTariffByName(@RequestParam String originCityName,@RequestParam String destinationCityName) throws TariffNotExistException {
        List<TariffProjection> tariffs = tariffController.getTariffByName(originCityName,destinationCityName);
        if (tariffs.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
        }else{
            throw new TariffNotExistException("Incorrect city names");
        }
    }

//    ESTO SE ROMPE DICE ALGO DE UN  * TOKEN
//    @GetMapping("/name/origin/")
//    public ResponseEntity<List<TariffProjection>> getTariffByOrigin(@RequestParam String originCityName) throws TariffNotExistException {
//        List<TariffProjection> tariffs = tariffController.getTariffByOrigin(originCityName);
//        if (tariffs.size() > 0){
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
//        }else{
//            throw new TariffNotExistException("Incorrect city names");
//        }
//    }
//
//    @GetMapping("/name/destination/")
//    public ResponseEntity<List<TariffProjection>> getTariffByDestination(@RequestParam String destinationCityName) throws TariffNotExistException {
//        List<TariffProjection> tariffs = tariffController.getTariffByDestination(destinationCityName);
//        if (tariffs.size() > 0){
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tariffs);
//        }else{
//            throw new TariffNotExistException("Incorrect city names");
//        }
//    }

    @PostMapping("/")
    public void addClient(@RequestBody TariffDto tariffDto) {
        tariffController.addClient(tariffDto);
    }

    

}
