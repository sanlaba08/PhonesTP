package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.projections.CallsProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CallBackControllerTest {
    private CallBackController callBackController;
    private CallsProjection callsProjection;

    @Mock
    private CallController callController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callBackController = new CallBackController(callController);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        callsProjection = factory.createProjection(CallsProjection.class);
    }

    @Test
    void getCallTest(){
        callsProjection.setOrigin_line("02235351545");
        callsProjection.setOrigin_city("Mar del Plata");
        callsProjection.setDestination_line("02236162410");
        callsProjection.setDestination_city("Buenos Aires");
        callsProjection.setDuration((long) 15);
        callsProjection.setTotal_price(20);
        callsProjection.setTotal_cost((float) 0.3);
        callsProjection.setCall_date(new Date());


        List<CallsProjection> callsProjections = new ArrayList<CallsProjection>();
        callsProjections.add(callsProjection);

        when(callController.getCall("41686701")).thenReturn(callsProjections);
        ResponseEntity<List<CallsProjection>> response = callBackController.getCall("41686701");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(callsProjections, response.getBody());
    }

    @Test
    void getAllCallEmpty() {
        List<CallsProjection> callList = new ArrayList<CallsProjection>();

        when(callController.getCall("41686701")).thenReturn(callList);
        ResponseEntity<List<CallsProjection>> response = callBackController.getCall("41686701");

        assertEquals(204, response.getStatusCodeValue());
    }
}

