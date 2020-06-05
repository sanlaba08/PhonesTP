package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.EmployeeDto;
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
    public void addEmployee(@RequestBody EmployeeDto employee){
        userController.addEmployee(employee);
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
    @GetMapping("/employee/")
    public ResponseEntity<List<EmployeesProjection>> getEmployee(@RequestParam String dni) throws UserNotExistException {
        List<EmployeesProjection> employee = userController.getEmployee(dni);

        if (employee.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employee);
        }else{
            throw new UserNotExistException("Enter a value to get a user.");
        }
    }
}
