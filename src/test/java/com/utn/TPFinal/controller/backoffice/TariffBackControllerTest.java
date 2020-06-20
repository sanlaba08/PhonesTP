package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.projections.TariffProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class TariffBackControllerTest {

    @InjectMocks
    private TariffBackController tariffBackController;

    @Mock
    private TariffController tariffController;//este devuelve el list

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffBackController = new TariffBackController(tariffController);
    }

    @Test
    void getAllTariffsOk() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        TariffProjection tariffProjection = factory.createProjection(TariffProjection.class);

        tariffProjection.setCity_origin("Mar del Plata");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setCost_per_minute((float) 30);
        tariffProjection.setPrice_per_minute((long) 400);

        List<TariffProjection> tariffList = new ArrayList<TariffProjection>();
        tariffList.add(tariffProjection);

        when(tariffController.getAllTariffs()).thenReturn(tariffList);
        ResponseEntity<List<TariffProjection>> response = tariffBackController.getAllTariffs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tariffList, response.getBody());
    }

    @Test
    void getTariffByIdOk() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        TariffProjection tariffProjection = factory.createProjection(TariffProjection.class);

        tariffProjection.setIdTariff(1);

        when(tariffController.getTariffById(1)).thenReturn(tariffProjection);

        ResponseEntity response = tariffBackController.getTariffById(1);

        assertNotNull(response);
        assertEquals(tariffProjection, response.getBody());
    }

    @Test
    void getTariffByNameOk() {

    }

    @Test
    void getAllTariffsEmpty() {
        List<TariffProjection> tariffList = new ArrayList<TariffProjection>();

        when(tariffController.getAllTariffs()).thenReturn(tariffList);
        ResponseEntity<List<TariffProjection>> response = tariffBackController.getAllTariffs();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getTariffByIdBad() {
//        ResponseEntity responseController = (ResponseEntity) tariffController.getTariffById(null);
//        when(responseController).thenReturn(null);
//        ResponseEntity responseBack = tariffBackController.getTariffById(1);
//        assertEquals(404, responseBack.getStatusCode());


//        responseEntity = ResponseEntity.notFound().build();
        ResponseEntity responseEntity = ResponseEntity.notFound().build();
        when(tariffBackController.getTariffById(0)).thenReturn(responseEntity);
    }

}