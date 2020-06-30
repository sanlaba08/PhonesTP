package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.exceptions.ValidationException;
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
    private CallsProjection topTenCallProjection;

    @Mock
    private CallService callService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callController = new CallController(callService);

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.callsProjection = factory.createProjection(CallsProjection.class);

        ProjectionFactory factoryTop = new SpelAwareProxyProjectionFactory();
        this.topTenCallProjection = factory.createProjection(CallsProjection.class);

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
    void getCall() throws ValidationException {
        when(callService.getListCall("42231235")).thenReturn(calls);

        List<CallsProjection> aux = callController.getCall("42231235");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callService,times(1)).getListCall("42231235");
    }

    @Test
    void getCallEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getCall("");
        });
    }

    @Test
    void getTopTenDestinations() throws ValidationException {
        topTenCallProjection.setFull_name_o("Santiago Labatut");
        topTenCallProjection.setDestination_city("Mar del Plata");
        topTenCallProjection.setCant(5);

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();
        topCalls.add(topTenCallProjection);

        when(callService.getTopTenDestinations("42231235")).thenReturn(topCalls);

        List<CallsProjection> aux = callController.getTopTenDestinations("42231235");

        assertNotNull(aux);
        assertEquals(aux, topCalls);
        verify(callService,times(1)).getTopTenDestinations("42231235");
    }

    @Test
    void getTopTenCallEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getTopTenDestinations("");
        });
    }

    @Test
    void getCallByDate() throws ValidationException {

        when(callService.getListCallByDate("42231235","2020-04-01","2020-06-20")).thenReturn(calls);

        List<CallsProjection> aux = callController.getCallByDate("42231235","2020-04-01","2020-06-20");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callService,times(1)).getListCallByDate("42231235","2020-04-01","2020-06-20");
    }

    @Test
    void getCallByDateEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getCallByDate("", "", "");
        });
    }

    @Test
    void getCallByDateDniEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getCallByDate("", "2020-6-25", "2020-6-27");
        });
    }

    @Test
    void getCallByDateFirstEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getCallByDate("41686701", "", "2020-6-27");
        });
    }

    @Test
    void getCallByDateSecondEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.getCallByDate("41686701", "2020-6-27", "");
        });
    }

    @Test
    void getCallByDateFirstBiggerThanSecond() {
        assertThrows(ValidationException.class, () -> {
            callController.getCallByDate("41686701", "2020-8-27", "2020-08-01");
        });
    }

    @Test
    void addCallOk() throws ValidationException {
        CallDto dto = new CallDto("123","321", (long) 3,new Date());
        when(callService.addCall(dto)).thenReturn(1);
        Integer response = callController.addCall(dto);
        assertEquals(response,1);
        verify(callService,times(1)).addCall(dto);
    }

    @Test
    void getAddCallEmpty() {
        assertThrows(ValidationException.class, () -> {
            callController.addCall(new CallDto("", "", null,null));
        });
    }
}