package com.utn.TPFinal.controller.administration;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.*;
import com.utn.TPFinal.exceptions.*;
import com.utn.TPFinal.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.utn.TPFinal.domain.UserType.Admin;
import static org.junit.Assert.assertEquals;
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
    void addEmployeeOk() throws URISyntaxException, EmployeeException, ValidationException {
        EmployeeDto employee = new EmployeeDto("Santiago", "Labatut", "41686701", "santi", 1);
        Integer idUser = 1;
        when(userController.addEmployee(employee)).thenReturn(idUser);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/admin/employee/" + idUser)).body(employee);

        assertNotNull(responseEntity);
        assertEquals(responseEntity, adminController.addEmployee(employee));
        verify(userController, times(1)).addEmployee(employee);
    }

    @Test()
    void addEmployeeExceptionEmployee() throws JpaSystemException, EmployeeException, ValidationException {
        EmployeeDto employee = new EmployeeDto("Santi", "Labatut", "41686701", "santi", 1);

        when(userController.addEmployee(employee)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

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
    void getEmployeeOk() throws ValidationException {
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        when(userController.getEmployee(user.getDni())).thenReturn(user);

        ResponseEntity response = adminController.getEmployee(user.getDni());

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(user), response);
    }

    @Test
    void getEmployeeBad() throws ValidationException {
        when(userController.getEmployee("42231235")).thenReturn(null);

        ResponseEntity responseController = adminController.getEmployee("42231235");
        assertEquals(HttpStatus.NOT_FOUND, responseController.getStatusCode());
    }

    @Test
    void addTariffOk() throws URISyntaxException, TariffException, ValidationException {
        TariffDto tariff = new TariffDto(1, 2, 10, 30);
        Integer idTariff = 1;
        when(tariffController.addTariff(tariff)).thenReturn(idTariff);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/admin/tariff/" + idTariff)).body(tariff);

        assertNotNull(responseEntity);
        assertEquals(responseEntity, adminController.addTariff(tariff));
        verify(tariffController, times(1)).addTariff(tariff);
    }

    @Test()
    void addTariffException() throws JpaSystemException, ValidationException {
        TariffDto tariff = new TariffDto(1, 2, 30, 40);

        when(tariffController.addTariff(tariff)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

        assertThrows(TariffException.class, () -> {
            adminController.addTariff(tariff);
        });

    }

    @Test
    void addCallOk() throws IncorrectDataCallException, URISyntaxException, ValidationException {
        CallDto call = new CallDto("02235351545", "02236162410", (long) 1200, new Date());
        Integer idCall = 1;
        when(callController.addCall(call)).thenReturn(idCall);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/admin/call/" + idCall)).body(call);

        assertNotNull(responseEntity);
        assertEquals(responseEntity, adminController.addCall(call));
        verify(callController, times(1)).addCall(call);
    }

    @Test()
    void addCallException() throws JpaSystemException, ValidationException {
        CallDto call = new CallDto("02235351545", "02236162410", (long) 1200, new Date());

        when(callController.addCall(call)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

        assertThrows(IncorrectDataCallException.class, () -> {
            adminController.addCall(call);
        });

    }

    @Test
    void modifyTariff() throws ValidationException, TariffException {
        ModifyTariffDto modifyTariffDto = new ModifyTariffDto(1,2,1);

        doNothing().when(tariffController).modifyTariff(modifyTariffDto);

        ResponseEntity responseEntity = adminController.modifyTariff(modifyTariffDto);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        verify(tariffController, times(1)).modifyTariff(modifyTariffDto);
    }

    @Test
    void modifyTariffException() throws ValidationException {
        ModifyTariffDto modifyTariffDto = new ModifyTariffDto(1,2,1);

        doThrow(new JpaSystemException(new RuntimeException(new SQLException()))).when(tariffController).modifyTariff(modifyTariffDto);

        assertThrows(TariffException.class, () -> {
            adminController.modifyTariff(modifyTariffDto);
        });
    }
}