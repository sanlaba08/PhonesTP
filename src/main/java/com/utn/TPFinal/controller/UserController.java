package com.utn.TPFinal.controller;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.Clients;
import com.utn.TPFinal.projections.Employees;
import com.utn.TPFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getUserAll();
    }

    @PostMapping("/employee")
    public void addEmployee(@RequestBody EmployeeDto employee){
        userService.addEmployee(employee);
    }

    @GetMapping("/employee")
    public List<Employees> getAllEmployee(){
        return userService.getListEmployee();
    }

    @GetMapping("/client")
    public List<Clients> getAllClients(){
        return userService.getListClients();
    }

    @GetMapping("/client/{IdUser}")
    public List<Clients> getClient(@PathVariable Integer IdUser){
        return userService.getClient(IdUser);
    }

    @GetMapping("/employee/{IdUser}")
    public List<Employees> getEmployee(@PathVariable Integer IdUser){
        return userService.getEmployee(IdUser);
    }



}
