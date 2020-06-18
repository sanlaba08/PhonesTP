package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.TariffProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffBackControllerTest {

    @InjectMocks
    private TariffBackController tariffBackController;

    @Mock
    private TariffController tariffController;//este devuelve el list

//    @BeforeEach
//    void setUp() {
//        initMocks(this);
//        tariffBackController = new TariffBackController(tariffController);
//    }

    @Test
    void getAllTariffsOk() {
        tariffController = mock(TariffController.class);
        tariffBackController = new TariffBackController(tariffController);

        List<TariffProjection> tariffs = tariffController.getAllTariffs();
        when(tariffBackController.getAllTariffs()).thenReturn(ResponseEntity.ok().body(tariffs));
    }

    @Test
    void getTariffByIdOk() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        TariffProjection tariffProjection = factory.createProjection(TariffProjection.class);

        Integer id = 1;

        tariffProjection.setCity_origin("Buenos Aires");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);

        when(tariffController.getTariffById(id)).thenReturn(tariffProjection);
    }

    @Test
    void getTariffByIdBad() {
        when(tariffController.getTariffById(null)).thenReturn(null);
    }


    @Test
    void getTariffByName() {
    }
}