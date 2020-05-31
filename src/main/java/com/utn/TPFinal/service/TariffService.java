package com.utn.TPFinal.service;

import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TariffService {
    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public TariffProjection getTariffById(Integer idTariff) {
        return tariffRepository.getTariff(idTariff);
    }
}
