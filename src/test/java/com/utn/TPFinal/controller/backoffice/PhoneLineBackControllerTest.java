package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.AddPhoneLineDto;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.exceptions.PhoneLineException;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineBackControllerTest {
    private PhoneLineBackController phoneLineBackController;

    @Mock
    private PhoneLineController phoneLineController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineBackController = new PhoneLineBackController(phoneLineController);
    }

    @Test
    void addPhoneLine() throws URISyntaxException, PhoneLineException, ValidationException {
        PhoneLineByUserDto phoneLineByUser = new PhoneLineByUserDto( 3, "Home");
        Integer idPhoneLineByUser = 1;
        when(phoneLineController.addPhoneLine(phoneLineByUser)).thenReturn(idPhoneLineByUser);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/backoffice/phoneline/" + idPhoneLineByUser)).body(phoneLineByUser);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity, phoneLineBackController.addPhoneLine(phoneLineByUser));
        verify(phoneLineController, times(1)).addPhoneLine(phoneLineByUser);
    }

    @Test
    void addPhoneLineByDni() throws URISyntaxException, PhoneLineException, ValidationException {
        AddPhoneLineDto phoneLineByUser = new AddPhoneLineDto( "3", "Home");
        Integer idPhoneLineByUser = 1;
        when(phoneLineController.addPhoneLineByDni(phoneLineByUser)).thenReturn(idPhoneLineByUser);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/backoffice/phoneline/dni/" + idPhoneLineByUser)).body(phoneLineByUser);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity, phoneLineBackController.addPhoneLineByDni(phoneLineByUser));
        verify(phoneLineController, times(1)).addPhoneLineByDni(phoneLineByUser);
    }

    @Test()
    void addPhoneLineException() throws JpaSystemException, ValidationException {
        PhoneLineByUserDto phoneLineByUser = new PhoneLineByUserDto( 3, "Home");

        when(phoneLineController.addPhoneLine(phoneLineByUser)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.addPhoneLine(phoneLineByUser);
        });

    }

    @Test()
    void addPhoneLineByDniException() throws JpaSystemException, ValidationException {
        AddPhoneLineDto phoneLineByUser = new AddPhoneLineDto( "3", "Home");

        when(phoneLineController.addPhoneLineByDni(phoneLineByUser)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.addPhoneLineByDni(phoneLineByUser);
        });

    }

    @Test
    void deletePhoneLine() throws PhoneLineException, ValidationException {
        doNothing().when(phoneLineController).suspendPhoneLine(1);
        ResponseEntity responseEntity = phoneLineBackController.suspendPhoneLine(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(phoneLineController, times(1)).suspendPhoneLine(1);
    }

    @Test()
    void deleteClientException() throws JpaSystemException, ValidationException {
        Integer idLine = 1;
        doThrow(new JpaSystemException(new RuntimeException(new SQLException()))).when(phoneLineController).suspendPhoneLine(idLine);

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.suspendPhoneLine(idLine);
        });

    }


    @Test
    void enablePhoneLine() throws PhoneLineException, ValidationException {
        doNothing().when(phoneLineController).enablePhoneLine(1);
        ResponseEntity responseEntity = phoneLineBackController.enablePhoneLine(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(phoneLineController, times(1)).enablePhoneLine(1);
    }

    @Test()
    void enableClientException() throws JpaSystemException, ValidationException {
        Integer idLine = 1;
        doThrow(new JpaSystemException(new RuntimeException(new SQLException()))).when(phoneLineController).enablePhoneLine(idLine);

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.enablePhoneLine(idLine);
        });

    }
}