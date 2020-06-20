package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.model.User;
import com.utn.TPFinal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void getAllEmployee() {
        List<User> list = new ArrayList<>();
        User user = new User(1,"Santiago",
                "Labatut","41686701",
                "admin123",1,null,
                null,null);

        list.add(user);

        when(userService.getAllEmployee()).thenReturn(list);
        List<User> aux = new ArrayList<>();
        aux = userController.getAllEmployee();
        assertNotNull(aux);
        assertEquals(aux, list);
        verify(userService,times(1)).getAllEmployee();
    }

    @Test
    void getAllClients() {
        List<User> list = new ArrayList<>();
        User a = new User();

        list.add(a);

        when(userService.getAllClient()).thenReturn(list);
        List<User> aux = new ArrayList<>();
        aux = userController.getAllClients();
        assertNotNull(aux);
        assertEquals(aux, list);
        verify(userService,times(1)).getAllClient();

    }

    @Test
    void getEmployee() {
        User employee = new User();
        when(userService.getEmployeeDni("41686701")).thenReturn(employee);

        User aux = userController.getEmployee("41686701");
        assertNotNull(aux);
        assertEquals(aux, employee);
        verify(userService,times(1)).getEmployeeDni("41686701");
    }

    @Test
    void getClient() {
        User client = new User();
        when(userService.findClientByDni("41686701")).thenReturn(client);

        User aux = userController.getClient("41686701");
        assertNotNull(aux);
        assertEquals(aux, client);
        verify(userService,times(1)).findClientByDni("41686701");

    }
}