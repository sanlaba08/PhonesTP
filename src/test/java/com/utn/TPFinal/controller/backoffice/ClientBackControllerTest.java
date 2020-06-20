package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.session.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static com.utn.TPFinal.model.UserType.Client;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ClientBackControllerTest {
    private ClientBackController clientBackController;
    @Mock
    private SessionManager sessionManager;
    @Mock
    private UserController userController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        clientBackController = new ClientBackController(sessionManager,userController);
    }
    @Test
    void addClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void suspendClient() {
    }

    @Test
    void modifyClient() {
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
    void reactiveClient() {
    }

}