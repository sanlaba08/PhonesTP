package com.utn.TPFinal.controller.administration;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.EmployeeException;
import com.utn.TPFinal.exceptions.IncorrectDataCallException;
import com.utn.TPFinal.exceptions.PhoneLineException;
import com.utn.TPFinal.projections.EmployeesProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {
    private final UserController userController;
    private final TariffController tariffController;
    private final CallController callController;

    @Autowired
    public AdminController(UserController userController, TariffController tariffController, CallController callController) {
        this.userController = userController;
        this.tariffController = tariffController;
        this.callController = callController;
    }

    // Alta de Empleado (Opcional).
    @PostMapping("employee/")
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee) throws EmployeeException {
        try {
            userController.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();//cambiar por uri
        } catch (JpaSystemException e) {
            throw new EmployeeException(e.getCause().getCause().getMessage());
        }
    }

    // Consulta de todos los empleados (Opcional).
    @GetMapping("employee/")
    public ResponseEntity<List<EmployeesProjection>> getAllEmployee() {
        List<EmployeesProjection> employees = userController.getAllEmployee();
        if (employees.size() > 0) {
            return ResponseEntity.ok().body(employees);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    // Consulta de empleado (Opcional).
    @GetMapping("employee/number")
    public ResponseEntity getEmployee(@RequestParam String dni) {
        ResponseEntity responseEntity = ResponseEntity.ok(userController.getEmployee(dni));
        if (responseEntity.getBody() == null) {
            responseEntity = ResponseEntity.notFound().build()/*.body(new ErrorResponseDto(8, "There is no employee with that dni..."))*/;
        }
        return responseEntity;
    }

    @PostMapping("tariff/")
    public ResponseEntity addTariff(@RequestBody TariffDto tariffDto) throws PhoneLineException {
        try {
            tariffController.addTariff(tariffDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();//uri
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

    @PostMapping("call/")
    public ResponseEntity addCall(@RequestBody CallDto callDto) throws IncorrectDataCallException {
        try{
            callController.addCall(callDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (JpaSystemException e){
            throw new IncorrectDataCallException(e.getCause().getCause().getMessage());
        }
    }

}
