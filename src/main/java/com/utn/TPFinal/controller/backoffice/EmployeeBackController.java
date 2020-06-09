package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.ErrorResponseDto;
import com.utn.TPFinal.exceptions.EmployeeException;
import com.utn.TPFinal.exceptions.TariffNotExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.projections.EmployeesProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
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
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee) throws EmployeeException {
        try {
            userController.addEmployee(employee);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new EmployeeException(e.getCause().getCause().getMessage());
        }
    }

    // Consulta de todos los empleados (Opcional).
    @GetMapping("/")
    public ResponseEntity<List<EmployeesProjection>> getAllEmployee() {
        List<EmployeesProjection> employees = userController.getAllEmployee();
//        if(employees.size() > 0)
//        {
        return ResponseEntity.ok().body(employees);
//        } else { PREGUNTAR SI ACA QUIERE QUE LE TIREMOS UNA EXCEPTION, POR Q TECNICAMENTE NO SE ROMPIO, SOLO DEVUELVE UN JSON VACIO
//            return ResponseEntity.badRequest().body(new ErrorResponseDto(8,"There are no employee loaded yet..."));
//        }
    }

    // Consulta de empleado (Opcional).
    @GetMapping("/number")
    public ResponseEntity getEmployee(@RequestParam String dni) throws UserNotExistException {
        ResponseEntity responseEntity = ResponseEntity.ok(userController.getEmployee(dni));
        if (responseEntity.getBody() != null) {
            return responseEntity;
        } else {
            responseEntity = ResponseEntity.badRequest().body(new ErrorResponseDto(8, "There is no employee with that dni..."));
            return responseEntity;
        }
    }
}
