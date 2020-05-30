package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
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

    /* 2) Manejo de Clientes (Service)*/

    // Alta de Cliente con su respectiva linea telefonica.
    public void addClientPhone(UserPhoneDto userPhone){
        userRepository.addClientPhone(userPhone.getName(), userPhone.getLastName(), userPhone.getDni(), userPhone.getPassword(), userPhone.getCity().getIdCity(), userPhone.getLineType().getIdTypeLine());
    }

    // Baja de Cliente con su respectiva linea telefonica.
    public void deleteClientPhone(Integer idUser) {
        userRepository.deleteClientPhone(idUser);
    }

    // Modificacion del Cliente y la linea telefonica.
    public void modifyClientPhone(UserPhoneModifyDto clientPhone) {
        userRepository.modifyClientPhone(clientPhone.getUser().getIdUser(), clientPhone.getName(), clientPhone.getLastName(), clientPhone.getPassword(), clientPhone.getCity().getIdCity(), clientPhone.getLineType().getIdTypeLine());
    }


    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    public void addEmployee(EmployeeDto employee){
        userRepository.addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), employee.getCity().getIdCity());
    }

    public List<EmployeesProjection> getListEmployee(){
        return userRepository.getEmployee();
    }

    public List<ClientsProjection> getListClients(){
        return userRepository.getClients();
    }

    public ClientsProjection getClient(Integer idUser) {
        return userRepository.getClient(idUser);
    }

    public List<EmployeesProjection> getEmployee(Integer idUser) {
        return userRepository.getEmployee(idUser);
    }






}
