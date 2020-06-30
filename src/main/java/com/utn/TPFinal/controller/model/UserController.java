package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.domain.User;
import com.utn.TPFinal.service.UserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Integer addClient(UserPhoneDto clientPhone) throws JpaSystemException, ValidationException {
        if (clientPhone.isValid()) {
            return userService.addClientPhone(clientPhone);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void deleteClient(String dni) throws JpaSystemException, ValidationException {
        if (!StringUtils.isBlank(dni)) {
            userService.deleteClientPhone(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void suspendClient(String dni) throws JpaSystemException, ValidationException {
        if (!StringUtils.isBlank(dni)) {
            userService.suspendClient(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void modifyClient(UserPhoneModifyDto clientPhone) throws JpaSystemException, ValidationException {
        if (clientPhone.isValid()) {
            userService.modifyClientPhone(clientPhone);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public List<User> getAllEmployee() {
        return userService.getAllEmployee();
    }

    public Integer addEmployee(EmployeeDto employee) throws JpaSystemException, ValidationException {
        if (employee.isValid()) {
            return userService.addEmployee(employee);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public List<User> getAllClients() {
        return userService.getAllClient();
    }

    public User getEmployee(String dni) throws ValidationException {
        if (!StringUtils.isBlank(dni)) {
            return userService.getEmployeeDni(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public User login(LoginRequestDto login) throws UserNotExistException, ValidationException {
        if (login.isValid()) {
            return userService.login(login);
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }

    public User getClient(String dni) throws ValidationException {
        if (!StringUtils.isBlank(dni)) {
            return userService.findClientByDni(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void reactiveClient(String dni) throws JpaSystemException, ValidationException {
        if (!StringUtils.isBlank(dni)) {
            userService.reactiveClient(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }
}

