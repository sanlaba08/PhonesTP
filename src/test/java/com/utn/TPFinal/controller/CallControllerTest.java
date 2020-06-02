package com.utn.TPFinal.controller;

import com.utn.TPFinal.service.CallService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CallControllerTest {

    CallController callController;
    CallService service;

    @Before
    public void setUp() {
        service = mock(CallService.class);
        callController = new CallController(service);
    }

    @Test
    public void testDniOk(){

    }
}
