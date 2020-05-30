package com.utn.TPFinal.controller;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
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


    /* 2) Manejo de Clientes (Controller)*/
    // Alta de Cliente con su respectiva linea telefonica.
    @PostMapping("/client")
    public void addClient(@RequestBody UserPhoneDto clientPhone){
        userService.addClientPhone(clientPhone);
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/client/{IdUser}")
    public void deleteClient(@PathVariable Integer IdUser){
        userService.deleteClientPhone(IdUser);
    }

    // Modificacion del Cliente y la linea telefonica. (VERIFICAR "NO SOPORTA EL METODO PUT")
    @PutMapping("/client/")
    public void modifyClient(@RequestBody UserPhoneModifyDto clientPhone){
        userService.modifyClientPhone(clientPhone);
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
    public List<EmployeesProjection> getAllEmployee(){
        return userService.getListEmployee();
    }

    @GetMapping("/client")
    public List<ClientsProjection> getAllClients(){
        return userService.getListClients();
    }

    @GetMapping("/client/{IdUser}")
    public ClientsProjection getClient(@PathVariable Integer IdUser){
        return userService.getClient(IdUser);
    }

    @GetMapping("/employee/{IdUser}")
    public List<EmployeesProjection> getEmployee(@PathVariable Integer IdUser){
        return userService.getEmployee(IdUser);
    }



}

