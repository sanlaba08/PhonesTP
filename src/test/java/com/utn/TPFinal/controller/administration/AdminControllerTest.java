package com.utn.TPFinal.controller.administration;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.EmployeeException;
import com.utn.TPFinal.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class AdminControllerTest {
    private AdminController adminController;

    @Mock
    private TariffController tariffController;
    @Mock
    private CallController callController;
    @Mock
    private UserController userController;
    @BeforeEach
    void setUp() {
        initMocks(this);
        adminController = new AdminController(userController, tariffController, callController);
    }

    @Test
    void addEmployeeOk() throws URISyntaxException, EmployeeException {
        EmployeeDto employee = new EmployeeDto("Santiago", "Labatut", "41686701", "santi", 1);
        Integer idUser = 1;
        when(userController.addEmployee(employee)).thenReturn(idUser);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/admin/employee/" + idUser)).body(employee);

        assertNotNull(responseEntity);
        assertEquals(responseEntity, adminController.addEmployee(employee));
        verify(userController, times(1)).addEmployee(employee);
    }

    @Test()
    void addEmployeeExceptionEmployee() throws URISyntaxException, EmployeeException {
        EmployeeDto employee = new EmployeeDto("Santiago", "Labatut", "41686701", "santi", 1);

        when(userController.addEmployee(employee)).thenThrow(new JpaSystemException(new RuntimeException()));

        assertThrows(EmployeeException.class, () -> {
            adminController.addEmployee(employee);
        });
    }


    @Test
    void getAllEmployeeOk() {
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);

        List<User> employees = new ArrayList<User>();
        employees.add(user);

        when(userController.getAllEmployee()).thenReturn(employees);
        ResponseEntity<List<User>> response = adminController.getAllEmployee();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employees, response.getBody());
    }

    @Test
    void getAllEmployeeEmpty() {
        List<User> users = new ArrayList<User>();

        when(userController.getAllEmployee()).thenReturn(users);
        ResponseEntity<List<User>> response = adminController.getAllEmployee();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getEmployeeOk() {
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        when(userController.getEmployee(user.getDni())).thenReturn(user);

        ResponseEntity response = adminController.getEmployee(user.getDni());

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    void getEmployeeBad() {
        when(userController.getEmployee("42231235")).thenReturn(null);

        ResponseEntity responseController = adminController.getEmployee("42231235");
        assertEquals(HttpStatus.NOT_FOUND, responseController.getStatusCode());
    }

    @Test
    void addTariff() {
//        TariffDto employee = new TariffDto("Mar del Plata", "Buenos Aires", 10, 30);
//        Integer idUser = 1;
//        when(userController.addEmployee(employee)).thenReturn(idUser);
//        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/admin/employee/" + idUser)).body(employee);
//
//        assertNotNull(responseEntity);
//        assertEquals(responseEntity, adminController.addEmployee(employee));
//        verify(userController, times(1)).addEmployee(employee);
    }

    @Test
    void addCall() {
    }
}