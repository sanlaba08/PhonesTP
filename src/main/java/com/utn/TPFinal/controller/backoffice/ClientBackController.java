package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/backoffice/client")
public class ClientBackController {
    private final UserController userController;

    @Autowired
    public ClientBackController(UserController userController) {
        this.userController = userController;
    }

    // Alta de Cliente con su respectiva linea telefonica.
    @PostMapping("/")
    public ResponseEntity addClient(@RequestBody UserPhoneDto clientPhone) throws UserAllReadyExistException, URISyntaxException, ValidationException {
        try {
            Integer idClient = userController.addClient(clientPhone);
            return ResponseEntity.created(new URI("http://localhost:8080/backoffice/client/" + idClient)).body(clientPhone);
        } catch (JpaSystemException e) {
            throw new UserAllReadyExistException(e.getCause().getCause().getMessage());
        }
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/")
    public ResponseEntity deleteClient(@RequestParam String dni) throws UserNotExistException, ValidationException {
        try {
            userController.deleteClient(dni);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/number")///number?dni=.....
    public ResponseEntity suspendClient(@RequestParam String dni) throws UserNotExistException, ValidationException {
        try {
            userController.suspendClient(dni);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity reactiveClient(@PathVariable String dni) throws UserNotExistException, ValidationException {
        try {
            userController.reactiveClient(dni);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity modifyClient(@RequestBody UserPhoneModifyDto clientPhone) throws UserNotExistException, ValidationException {
        try {
            userController.modifyClient(clientPhone);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllClients() {
        List<User> clients = userController.getAllClients();

        if (clients.size() > 0) {
            return ResponseEntity.ok().body(clients);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/number")
    public ResponseEntity<User> getClientPhoneLine(@RequestParam String dni) throws ValidationException {
        User clients = userController.getClient(dni);
        if (clients != null) {
            return ResponseEntity.ok().body(clients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
