package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.AddPhoneLineDto;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.ClientProjection;
import com.utn.TPFinal.projections.TariffProjection;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineControllerTest {

    private PhoneLineController phoneLineController;
    private ClientProjection clientProjection;

    @Mock
    private PhoneLineService phoneLineService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineController = new PhoneLineController(phoneLineService);
        ProjectionFactory factoryClient = new SpelAwareProxyProjectionFactory();
        clientProjection = factoryClient.createProjection(ClientProjection.class);
    }

    @Test
    void addPhoneLine() throws ValidationException {
        PhoneLineByUserDto phoneLineByUserDto = new PhoneLineByUserDto(1,"Mobile");
        when(phoneLineService.addPhoneLine(phoneLineByUserDto)).thenReturn(1);
        Integer response = phoneLineController.addPhoneLine(phoneLineByUserDto);
        assertEquals(response,1);
        verify(phoneLineService,times(1)).addPhoneLine(phoneLineByUserDto);
    }

    @Test
    void addPhoneLineByDni() throws ValidationException {
        AddPhoneLineDto phoneLineByUserDto = new AddPhoneLineDto("1","Mobile");
        when(phoneLineService.addPhoneLineByDni(phoneLineByUserDto)).thenReturn(1);
        Integer response = phoneLineController.addPhoneLineByDni(phoneLineByUserDto);
        assertEquals(response,1);
        verify(phoneLineService,times(1)).addPhoneLineByDni(phoneLineByUserDto);
    }

    @Test
    void addPhoneLineEmpty() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.addPhoneLine(new PhoneLineByUserDto(1, ""));
        });
    }

    @Test
    void addPhoneLineByDniException() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.addPhoneLineByDni(new AddPhoneLineDto("", ""));
        });
    }

    @Test
    void suspendPhoneLine() throws ValidationException {

        doNothing().when(phoneLineService).suspendPhoneLine(1);
        phoneLineController.suspendPhoneLine(1);
        verify(phoneLineService,times(1)).suspendPhoneLine(1);
    }

    @Test
    void suspendPhoneLineNull() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.suspendPhoneLine(null);
        });
    }

    @Test
    void suspendPhoneLineNegativeId() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.suspendPhoneLine(-30);
        });
    }

    @Test
    void enablePhoneLine() throws ValidationException {
        doNothing().when(phoneLineService).enablePhoneLine(1);
        phoneLineController.enablePhoneLine(1);
        verify(phoneLineService,times(1)).enablePhoneLine(1);
    }

    @Test
    void enablePhoneLineNull() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.enablePhoneLine(null);
        });
    }

    @Test
    void enablePhoneLineNegativeId() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.enablePhoneLine(-30);
        });
    }

    @Test
    void getClientLine() throws ValidationException {
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineService.getClientLine(clientProjection.getDni())).thenReturn(clientProjection);

        ClientProjection aux = phoneLineController.getClientLine(clientProjection.getDni());

        assertNotNull(aux);
        assertEquals(aux, clientProjection);
        verify(phoneLineService,times(1)).getClientLine(clientProjection.getDni());

    }

    @Test
    void getClientLineEmpty() {
        assertThrows(ValidationException.class, () -> {
            phoneLineController.getClientLine("");
        });
    }
}