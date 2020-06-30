package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.ModifyTariffDto;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public TariffProjection getTariffById(Integer idTariff) throws ValidationException {
        if (idTariff != null && idTariff > 0) {
            return tariffService.getTariffById(idTariff);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public TariffProjection getTariffByName(String originCityName, String destinationCityName) throws ValidationException {
        if (!StringUtils.isBlank(originCityName) && !StringUtils.isBlank(destinationCityName)) {
            return tariffService.getTariffByName(originCityName, destinationCityName);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public Integer addTariff(TariffDto tariffDto) throws ValidationException {
        if (tariffDto.isValid()) {
            return tariffService.addTariff(tariffDto);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void modifyTariff(ModifyTariffDto modifyTariffDto) throws ValidationException {
        if (modifyTariffDto.isValid()){
            tariffService.modifyTariff(modifyTariffDto);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }
}
