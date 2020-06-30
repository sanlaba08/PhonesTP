package com.utn.TPFinal.controller.administration;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.ModifyTariffDto;
import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.exceptions.*;
import com.utn.TPFinal.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee created"),
            @ApiResponse(code = 400, message = "The DNI already exists or some parameter was not loaded"),
            @ApiResponse(code = 400, message = "Wrong parameters (empty, null, or wrong)")
            })
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employee) throws JpaSystemException, EmployeeException, URISyntaxException, ValidationException {
        try {
            Integer idEmployee = userController.addEmployee(employee);
            return ResponseEntity.created(new URI("http://localhost:8080/admin/employee/" + idEmployee)).body(employee);
        } catch (JpaSystemException e) {
            throw new EmployeeException(e.getCause().getCause().getMessage());
        }
    }

    // Consulta de todos los empleados (Opcional).
    @GetMapping("employee/")
    @ApiOperation(value = "Bring a list with all the employees loaded")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bring a list with all the employees available"),
            @ApiResponse(code = 204, message = "There is no employees loaded yet or all of them are set as unavailable"),
            @ApiResponse(code = 400, message = "Wrong parameters (empty, null, or wrong)")
    })
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
    @ApiOperation(value = "Returns the employee to whom the dni corresponds")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the specified employee"),
            @ApiResponse(code = 404, message = "There is no employee with that dni"),
            @ApiResponse(code = 400, message = "Wrong parameters (empty, null, or wrong)")
    })
    public ResponseEntity getEmployee(@RequestParam String dni) throws ValidationException {
        User employee = userController.getEmployee(dni);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employee);
        }
    }

    //Alta de tarifas (Opcional)
    @PostMapping("tariff/")
    @ApiOperation(value = "Create a new tariff")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The tariff was created"),
            @ApiResponse(code = 400, message = "Wrong parameters (empty, null, or wrong)")
    })
    public ResponseEntity addTariff(@RequestBody TariffDto tariffDto) throws TariffException, URISyntaxException, ValidationException {
        try {
            Integer idTariff = tariffController.addTariff(tariffDto);
            return ResponseEntity.created(new URI("http://localhost:8080/admin/tariff/" + idTariff)).body(tariffDto);
        } catch (JpaSystemException e) {
            throw new TariffException(e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("tariff/")
    public ResponseEntity modifyTariff(@RequestBody ModifyTariffDto modifyTariffDto) throws TariffException, ValidationException {
        try {
            tariffController.modifyTariff(modifyTariffDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (JpaSystemException e) {
            throw new TariffException(e.getCause().getCause().getMessage());
        }
    }

    //INFRAESTRUCTURA = Realizar llamada.
    @PostMapping("call/")
    public ResponseEntity addCall(@RequestBody CallDto callDto) throws IncorrectDataCallException, URISyntaxException, ValidationException {
        try {
            Integer idCall = callController.addCall(callDto);
            return ResponseEntity.created(new URI("http://localhost:8080/admin/call/" + idCall)).body(callDto);
        } catch (JpaSystemException e) {
            throw new IncorrectDataCallException(e.getCause().getCause().getMessage());
        }
    }

}
