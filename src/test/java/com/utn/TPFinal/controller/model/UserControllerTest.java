package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.*;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.domain.User;
import com.utn.TPFinal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.utn.TPFinal.domain.UserType.Admin;
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
        User user = new User(1, "Santiago",
                "Labatut", "41686701",
                "admin123", 1, null,
                null, null);

        list.add(user);

        when(userService.getAllEmployee()).thenReturn(list);
        List<User> aux = new ArrayList<>();
        aux = userController.getAllEmployee();
        assertNotNull(aux);
        assertEquals(aux, list);
        verify(userService, times(1)).getAllEmployee();
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
        verify(userService, times(1)).getAllClient();

    }

    @Test
    void getEmployee() throws ValidationException {
        User employee = new User();
        when(userService.getEmployeeDni("41686701")).thenReturn(employee);

        User aux = userController.getEmployee("41686701");
        assertNotNull(aux);
        assertEquals(aux, employee);
        verify(userService, times(1)).getEmployeeDni("41686701");
    }

    @Test
    void getEmployeeEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.getEmployee("");
        });
    }

    @Test
    void getClient() throws ValidationException {
        User client = new User();
        when(userService.findClientByDni("41686701")).thenReturn(client);

        User aux = userController.getClient("41686701");
        assertNotNull(aux);
        assertEquals(aux, client);
        verify(userService, times(1)).findClientByDni("41686701");
    }

    @Test
    void getClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.getClient("");
        });
    }

    @Test
    void addClientOk() throws ValidationException {
        UserPhoneDto userPhoneDto = new UserPhoneDto("manu","sure","4321","123",1,"Home");
        when(userService.addClientPhone(userPhoneDto)).thenReturn(1);
        Integer response = userController.addClient(userPhoneDto);
        assertEquals(response, 1);
        verify(userService, times(1)).addClientPhone(userPhoneDto);
    }

    @Test
    void addClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.addClient(new UserPhoneDto("","","","",null,""));
        });
    }

    @Test
    void deleteClientOk() throws ValidationException {
        doNothing().when(userService).deleteClientPhone("123");
        userController.deleteClient("123");
        verify(userService, times(1)).deleteClientPhone(any());
    }

    @Test
    void deleteClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.deleteClient("");
        });
    }

    @Test
    void suspendClient() throws ValidationException {
        doNothing().when(userService).suspendClient("123");
        userController.suspendClient("123");
        verify(userService, times(1)).suspendClient("123");
    }

    @Test
    void suspendClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.suspendClient("");
        });
    }

    @Test
    void modifyClient() throws ValidationException {
        UserPhoneModifyDto user = new UserPhoneModifyDto(1, "manu", "sure", "123", "321", 1);
        doNothing().when(userService).modifyClientPhone(user);
        userController.modifyClient(user);
        verify(userService, times(1)).modifyClientPhone(user);
    }

    @Test
    void modifyClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.modifyClient(new UserPhoneModifyDto(3,"","","","",null));
        });
    }

    @Test
    void addEmployee() throws ValidationException {
        EmployeeDto employeeDto = new EmployeeDto("manu", "sure", "321", "321", 1);
        when(userService.addEmployee(employeeDto)).thenReturn(1);
        Integer response = userController.addEmployee(employeeDto);
        assertEquals(response, 1);
        verify(userService, times(1)).addEmployee(employeeDto);
    }

    @Test
    void addEmployeeEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.addEmployee(new EmployeeDto("", "", "", "", null));
        });
    }

    @Test
    void reactiveClient() throws ValidationException {
        doNothing().when(userService).reactiveClient("123");
        userController.reactiveClient("123");
        verify(userService, times(1)).reactiveClient("123");
    }

    @Test
    void reactiveClientEmpty() {
        assertThrows(ValidationException.class, () -> {
            userController.reactiveClient("");
        });
    }

    @Test
    void loginOk() throws UserNotExistException, ValidationException {
        User loggedUser = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        LoginRequestDto login = new LoginRequestDto("41686701", "santi");
        //Cuando llame al mock service.login devuelvo el logged user
        when(userService.login(login)).thenReturn(loggedUser);

        User returnedUser = userController.login(login);

        //Hacemos los assert
        assertEquals(loggedUser.getDni(), returnedUser.getDni());
        assertEquals(loggedUser.getUserPassword(), returnedUser.getUserPassword());
        verify(userService, times(1)).login(login);
    }

    @Test
    public void testLoginUserNotFound() throws UserNotExistException, ValidationException {
        LoginRequestDto loginRequestDto = new LoginRequestDto("41686701", "santi");
        when(userService.login(loginRequestDto)).thenThrow(new UserNotExistException("Incorrect user data"));
        assertThrows(UserNotExistException.class, () -> {
            userController.login(loginRequestDto);
        });
    }

    @Test
    public void testLoginInvalidData() throws UserNotExistException, ValidationException {
        assertThrows(ValidationException.class, () -> {
            userController.login(new LoginRequestDto(null, "bla"));
        });
    }


}