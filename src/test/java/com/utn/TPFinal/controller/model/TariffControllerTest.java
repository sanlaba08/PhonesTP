package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffControllerTest {

    TariffController tariffController;

    @Mock
    TariffService tariffService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffController = new TariffController(tariffService);
    }

    @Test
    void getAllTariffsOk() {

        List<TariffProjection> tarifs = tariffService.getAllTariffs();

        Mockito.when(tariffController.getAllTariffs()).thenReturn(tarifs);

    }

    @Test
    void getTariffByIdOk() {
        TariffProjection tariffProjection = tariffService.getTariffById(1);
        Mockito.when(tariffController.getTariffById(1)).thenReturn(tariffProjection);
    }

    @Test
    void getTariffByIdBad() {
        TariffProjection tariffProjection = tariffService.getTariffById(null);
        Mockito.when(tariffController.getTariffById(1)).thenReturn(tariffProjection);
    }

    @Test
    void getTariffByName() {
    }

    @Test
    void addTariff() {
    }
}