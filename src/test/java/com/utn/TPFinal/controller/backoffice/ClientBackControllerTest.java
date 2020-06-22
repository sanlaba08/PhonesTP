package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.session.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static com.utn.TPFinal.model.UserType.Client;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientBackControllerTest {
    private ClientBackController clientBackController;

    @Mock
    private UserController userController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        clientBackController = new ClientBackController(userController);
    }
    @Test
    void addClient() throws URISyntaxException, UserAllReadyExistException {
        UserPhoneDto userPhone = new UserPhoneDto("Santiago", "Labatut", "41686701", "santi",1, "Home");
        Integer idUserPhone = 1;
        when(userController.addClient(userPhone)).thenReturn(idUserPhone);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/backoffice/client/" + idUserPhone)).body(userPhone);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity, clientBackController.addClient(userPhone));
        verify(userController, times(1)).addClient(userPhone);
    }

    @Test
    void deleteClient() throws UserNotExistException {
        doNothing().when(userController).deleteClient("41686701");
        ResponseEntity responseEntity = clientBackController.deleteClient("41686701");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userController, times(1)).deleteClient("41686701");
    }

    @Test
    void suspendClient() throws UserNotExistException {
        doNothing().when(userController).suspendClient("41686701");
        ResponseEntity responseEntity = clientBackController.suspendClient("41686701");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userController, times(1)).suspendClient("41686701");
    }

    @Test
    void modifyClient() throws UserNotExistException {
        UserPhoneModifyDto userPhoneModify = new UserPhoneModifyDto(1,"Santiago", "Labatut", "41686701", "santi",1, "Home");
        doNothing().when(userController).modifyClient(userPhoneModify);

        ResponseEntity responseEntity = clientBackController.modifyClient(userPhoneModify);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        verify(userController, times(1)).modifyClient(userPhoneModify);

    }

    @Test
    void getAllClientsOk(){
        User user = new User(1,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);

        List<User> clients = new ArrayList<User>();
        clients.add(user);

        when(userController.getAllClients()).thenReturn(clients);
        ResponseEntity<List<User>> response = clientBackController.getAllClients();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clients, response.getBody());
    }

    @Test
    void getAllEmployeeEmpty() {
        List<User> clients = new ArrayList<>();

        when(userController.getAllClients()).thenReturn(clients);
        ResponseEntity<List<User>> response = clientBackController.getAllClients();

        assertEquals(204, response.getStatusCodeValue());
    }


    @Test
    void getClientPhoneLineOk() {
        User user = new User(1,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        when(userController.getClient(user.getDni())).thenReturn(user);

        ResponseEntity response = clientBackController.getClientPhoneLine(user.getDni());

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    void getClientPhoneLineBad() {
        when(userController.getEmployee("42231235")).thenReturn(null);

        ResponseEntity responseController = clientBackController.getClientPhoneLine("42231235");
        assertEquals(HttpStatus.NOT_FOUND, responseController.getStatusCode());
    }

    @Test
    void reactiveClient() throws UserNotExistException {
        doNothing().when(userController).reactiveClient("41686701");
        ResponseEntity responseEntity = clientBackController.reactiveClient("41686701");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userController, times(1)).reactiveClient("41686701");
    }

}