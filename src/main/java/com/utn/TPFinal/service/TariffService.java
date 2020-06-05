package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {
    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<TariffProjection> getAllTariffs() {
        return tariffRepository.getAllTariffs();
    }

    public List<TariffProjection> getTariffById(Integer idTariff) {
        return tariffRepository.getTariffById(idTariff);
    }

    public List<TariffProjection> getTariffByName(String originCityName, String destinationCityName) {
        return tariffRepository.getTariffByName(originCityName,destinationCityName);
    }

//
//    public List<TariffProjection> getTariffByOrigin(String originCityName) {
//        return tariffRepository.getTariffByOrigin(originCityName);
//    }
//
//    public List<TariffProjection> getTariffByDestination(String destinationCityName) {
//        return tariffRepository.getTariffByDestination(destinationCityName);
//    }

    public void addClient(TariffDto tariffDto) {
        tariffRepository.addClient(tariffDto.getOriginCityName(),tariffDto.getDestinationCityName(),tariffDto.getPricePerMinute(),tariffDto.getCostPerMinute());
    }
}
