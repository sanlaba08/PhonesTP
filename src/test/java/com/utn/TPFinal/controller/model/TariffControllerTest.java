package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.dto.ModifyTariffDto;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffControllerTest {

    private TariffController tariffController;
    private TariffProjection tariffProjection;

    @Mock
    private TariffService tariffService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffController = new TariffController(tariffService);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.tariffProjection = factory.createProjection(TariffProjection.class);

        tariffProjection.setIdTariff(1);
        tariffProjection.setCity_origin("Buenos Aires");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);
    }

    @Test
    void getAllTariffsOk() {

        List<TariffProjection> tarifs = new ArrayList<>();
        tarifs.add(tariffProjection);

        when(tariffService.getAllTariffs()).thenReturn(tarifs);

        List<TariffProjection> tarifsAux = tariffController.getAllTariffs();

        assertNotNull(tarifsAux);
        assertEquals(tarifs, tarifsAux);
        verify(tariffService,times(1)).getAllTariffs();
    }

    @Test
    void getTariffByIdOk() throws ValidationException {

        when(tariffService.getTariffById(1)).thenReturn(tariffProjection);

        TariffProjection aux = tariffController.getTariffById(1);

        assertNotNull(aux);
        assertEquals(aux, tariffProjection);
        verify(tariffService,times(1)).getTariffById(1);
    }

    @Test
    void getTariffByIdNull() {
        assertThrows(ValidationException.class, () -> {
            tariffController.getTariffById(null);
        });
    }

    @Test
    void getTariffByIdNegativeId() {
        assertThrows(ValidationException.class, () -> {
            tariffController.getTariffById(-30);
        });
    }

    @Test
    void getTariffByNameOk() throws ValidationException {

        when(tariffController.getTariffByName("Buenos Aires","Buenos Aires")).thenReturn(tariffProjection);
        TariffProjection aux = tariffService.getTariffByName("Buenos Aires","Buenos Aires");

        assertNotNull(aux);
        assertEquals(aux,tariffProjection);
    }

    @Test
    void getTariffByNameEmpty() {
        assertThrows(ValidationException.class, () -> {
            tariffController.getTariffByName("", "");
        });
    }

    @Test
    void getTariffByFirstCityNameEmpty() {
        assertThrows(ValidationException.class, () -> {
            tariffController.getTariffByName("Mar del Plata", "");
        });
    }

    @Test
    void getTariffBySecondCityNameEmpty() {
        assertThrows(ValidationException.class, () -> {
            tariffController.getTariffByName("", "Mar del Plata");
        });
    }

    @Test
    void addTariffOk() throws ValidationException {
        TariffDto dto =  new TariffDto(1,2,3,2);
        when(tariffService.addTariff(dto)).thenReturn(1);
        Integer response = tariffController.addTariff(dto);
        assertEquals(response,1);
        verify(tariffService,times(1)).addTariff(dto);
    }

    @Test
    void addTariffNull() {
        assertThrows(ValidationException.class, () -> {
            tariffController.addTariff(new TariffDto(null, null, -19, -32));
        });
    }

    @Test
    void modifyTariff() throws ValidationException {
        ModifyTariffDto modifyTariffDto = new ModifyTariffDto(1,4,3);
        doNothing().when(tariffService).modifyTariff(modifyTariffDto);
        tariffController.modifyTariff(modifyTariffDto);
        verify(tariffService, times(1)).modifyTariff(modifyTariffDto);
    }

    @Test
    void modifyTariffNegative() {
        assertThrows(ValidationException.class, () -> {
            tariffController.modifyTariff(new ModifyTariffDto(-6, -19, -32));
        });
    }
}