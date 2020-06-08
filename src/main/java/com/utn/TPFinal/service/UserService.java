package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.InvalidClientException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
import com.utn.TPFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* 2) Manejo de Clientes (Service)*/

    // Alta de Cliente con su respectiva linea telefonica.
    public void addClientPhone(UserPhoneDto userPhone) throws JpaSystemException {
       userRepository.addClientPhone(userPhone.getName(), userPhone.getLastName(), userPhone.getDni(), userPhone.getPassword(), userPhone.getCity(), userPhone.getLineType());
    }

    // Baja de Cliente con su respectiva linea telefonica.
    public void deleteClientPhone(String dni) throws UserNotExistException{
        userRepository.deleteClientPhone(dni);
    }

    // Modificacion del Cliente y la linea telefonica.
    public void modifyClientPhone(UserPhoneModifyDto clientPhone) {
        userRepository.modifyClientPhone(clientPhone.getUser(), clientPhone.getName(), clientPhone.getLastName(), clientPhone.getDni(),clientPhone.getPassword(), clientPhone.getCity(), clientPhone.getLineType());
    }


    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    public void addEmployee(EmployeeDto employee){
        userRepository.addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), employee.getCity());
    }

    public List<EmployeesProjection> getListEmployee(){
        return userRepository.getEmployee();
    }

    public List<ClientsProjection> getListClients(){
        return userRepository.getClients();
    }


    public EmployeesProjection getEmployeeDni(String dni) throws UserNotExistException {
        return userRepository.getEmployeeDni(dni);
    }

    public User login(LoginRequestDto login) throws UserNotExistException {
        User user = userRepository.getByUsername(login.getDni(), login.getPassword());
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotExistException("User not exist"));
    }

    public ClientsProjection getClientDni(String dni) {
        return userRepository.getClientDni(dni);
    }
}
