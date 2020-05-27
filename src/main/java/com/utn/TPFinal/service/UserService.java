package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.proyections.Clients;
import com.utn.TPFinal.proyections.Employees;
import com.utn.TPFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    public void addEmployee(EmployeeDto employee){
        userRepository.addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), 6);
    }

    public List<Employees> getListEmployee(){
        return userRepository.getEmployee();
    }

    public List<Clients> getListClients(){
        return userRepository.getClients();
    }


    public List<Clients> getClient(Integer idUser) {
        return userRepository.getClient(idUser);
    }

    public List<Employees> getEmployee(Integer idUser) {
        return userRepository.getEmployee(idUser);
    }


}
