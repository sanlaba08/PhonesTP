package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.service.BillService;
import com.utn.TPFinal.service.PhoneLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineControllerTest {

    private PhoneLineController phoneLineController;

    @Mock
    private PhoneLineService phoneLineService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineController = new PhoneLineController(phoneLineService);
    }

    @Test
    void addPhoneLine() {

    }

    @Test
    void deletePhoneLine() {
//        doNothing().when(phoneLineService).deletePhoneLine(any());
//        phoneLineController.deletePhoneLine(1);
//        verify(phoneLineService,times())
//
    }

    @Test
    void enablePhoneLine() {
    }
}