package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.exceptions.TariffNotExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.projections.EmployeesProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeBackController {
    private final UserController userController;

    @Autowired
    public EmployeeBackController(UserController userController) {
        this.userController = userController;
    }

    // Alta de Empleado (Opcional).
    @PostMapping("/")
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee){
        userController.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    // Consulta de todos los empleados (Opcional).
    @GetMapping("/")
    public ResponseEntity<List<EmployeesProjection>> getAllEmployee() throws UserNotExistException{
        List<EmployeesProjection> employees = userController.getAllEmployee();
        if (employees.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employees);
        }else{
            throw new UserNotExistException("Enter a value to get a user.");
        }

    }

    // Consulta de empleado (Opcional).
    @GetMapping("/number")
    public ResponseEntity getEmployee(@RequestParam String dni) throws UserNotExistException {
        ResponseEntity responseEntity;
        try{
            responseEntity = ResponseEntity.ok(userController.getEmployee(dni));
        } catch (UserNotExistException e){
            responseEntity = ResponseEntity.badRequest().build();
            throw new UserNotExistException("Dni not exist");
        }
        return responseEntity;
    }
}
