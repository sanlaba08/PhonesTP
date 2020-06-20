package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.controller.backoffice.TariffBackController;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.service.CallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CallControllerTest {
    private CallController callController;
    private CallsProjection callsProjection;
    private List<CallsProjection> calls;

    @Mock
    private CallService callService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callController = new CallController(callService);

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.callsProjection = factory.createProjection(CallsProjection.class);

        callsProjection.setOrigin_line("02236493784");
        callsProjection.setOrigin_city("Mar del Plata");
        callsProjection.setDestination_line("02216986864");
        callsProjection.setDestination_city("La Plata");
        callsProjection.setDuration((long) 50);
        callsProjection.setTotal_price(500);
        callsProjection.setTotal_cost((float) 200);
        callsProjection.setCall_date(new Date());

        calls = new ArrayList<>();
        calls.add(callsProjection);
    }

    @Test
    void getCall() {
        when(callService.getListCall("42231235")).thenReturn(calls);

        List<CallsProjection> aux = callController.getCall("42231235");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callService,times(1)).getListCall("42231235");
    }

    @Test
    void getTopTenDestinations() {

        when(callService.getTopTenDestinations("42231235")).thenReturn(calls);

        List<CallsProjection> aux = callController.getTopTenDestinations("42231235");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callService,times(1)).getTopTenDestinations("42231235");
    }

    @Test
    void getCallByDate() {

        when(callService.getListCallByDate("42231235","2020-04-01","2020-06-20")).thenReturn(calls);

        List<CallsProjection> aux = callController.getCallByDate("42231235","2020-04-01","2020-06-20");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callService,times(1)).getListCallByDate("42231235","2020-04-01","2020-06-20");
    }

    @Test
    void addCall() {
    }
}