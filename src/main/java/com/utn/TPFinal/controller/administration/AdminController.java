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
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee) throws EmployeeException, URISyntaxException {
        try {
           Integer idEmployee = userController.addEmployee(employee);
           return ResponseEntity.created(new URI("http://localhost:8080/admin/employee/" + idEmployee)).body(employee);
        } catch (JpaSystemException e) {
            throw new EmployeeException(e.getCause().getCause().getMessage());
        }
    }

    // Consulta de todos los empleados (Opcional).
    @GetMapping("employee/")
    public ResponseEntity<List<User>> getAllEmployee() {
        List<User> employees = userController.getAllEmployee();
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
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PostMapping("tariff/")
    public ResponseEntity addTariff(@RequestBody TariffDto tariffDto) throws PhoneLineException, URISyntaxException {
        try {
            Integer idTariff = tariffController.addTariff(tariffDto);
            return ResponseEntity.created(new URI("http://localhost:8080/admin/tariff/" + idTariff)).body(tariffDto);
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

    @PostMapping("call/")
    public ResponseEntity addCall(@RequestBody CallDto callDto) throws IncorrectDataCallException, URISyntaxException {
        try{
            Integer idCall = callController.addCall(callDto);
            return ResponseEntity.created(new URI("http://localhost:8080/admin/call/" + idCall)).body(callDto);
        } catch (JpaSystemException e){
            throw new IncorrectDataCallException(e.getCause().getCause().getMessage());
        }
    }
}
