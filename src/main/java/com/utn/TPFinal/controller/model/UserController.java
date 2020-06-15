package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
import com.utn.TPFinal.service.UserService;
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

    public void addClient(UserPhoneDto clientPhone) throws JpaSystemException {
       userService.addClientPhone(clientPhone);
    }

    public void deleteClient(String dni) throws JpaSystemException{
        userService.deleteClientPhone(dni);
    }

    public void suspendClient(String dni) throws UserNotExistException{
        userService.suspendClient(dni);
    }

    public void modifyClient(UserPhoneModifyDto clientPhone) throws JpaSystemException{
        userService.modifyClientPhone(clientPhone);
    }

    public List<User> getAll() throws UserNotExistException{
        return userService.getUserAll();
    }

    public void addEmployee(EmployeeDto employee) throws JpaSystemException {
        userService.addEmployee(employee);
    }

    public List<EmployeesProjection> getAllEmployee(){
        return userService.getListEmployee();
    }

    public List<ClientsProjection> getAllClients() throws UserNotExistException{
        return userService.getListClients();
    }

    public EmployeesProjection getEmployee(String dni) {
       return userService.getEmployeeDni(dni);
    }

    public User login(LoginRequestDto login) throws UserNotExistException, ValidationException {
        if ((login.getDni() != null) && (login.getPassword() != null)) {
            return userService.login(login);
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }

    public List<ClientsProjection> getClient(String dni) throws UserNotExistException {
        return userService.getClientDni(dni);
    }

}

