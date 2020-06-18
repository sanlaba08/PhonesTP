package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
import com.utn.TPFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Integer addClient(UserPhoneDto clientPhone) throws JpaSystemException {
       return userService.addClientPhone(clientPhone);
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

    public List<User> getAllEmployee() throws UserNotExistException{
        List<User> users = userService.getUserAll();
        List<User> ans = new ArrayList<>();

        for (User aux: users) {
            if(aux.getUserType().equals(UserType.Employee)){
                ans.add(aux);
            }
        }
        return ans;
    }

    public Integer addEmployee(EmployeeDto employee) throws JpaSystemException {
        return userService.addEmployee(employee);
    }


    public List<User> getAllClients() throws UserNotExistException{
        List<User> users = userService.getUserAll();
        List<User> ans = new ArrayList<>();

        for (User aux: users) {
            if(aux.getUserType().equals(UserType.Client)){
                ans.add(aux);
            }
        }
        return ans;
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

    public void reactiveClient(String dni) throws UserNotExistException {
        userService.reactiveClient(dni);
    }
}

