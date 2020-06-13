package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.IncorrectDataClientPhoneException;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/backoffice/client")
public class ClientBackController {
    private final SessionManager sessionManager;
    private final UserController userController;

    @Autowired
    public ClientBackController(SessionManager sessionManager, UserController userController) {
        this.sessionManager = sessionManager;
        this.userController = userController;
    }

    /* 2) Manejo de Clientes (Controller)*/
    // Alta de Cliente con su respectiva linea telefonica.
    @PostMapping("/")
    public ResponseEntity addClient(@RequestHeader("Authorization") String sessionToken, @RequestBody UserPhoneDto clientPhone) throws UserAllReadyExistException {
        try {
            userController.addClient(clientPhone);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (JpaSystemException e) {
            throw new UserAllReadyExistException(e.getCause().getCause().getMessage());
        }
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/")
    public ResponseEntity deleteClient(@RequestParam String dni) throws UserNotExistException {
        try {
            userController.deleteClient(dni);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/number")///number?dni=.....
    public ResponseEntity suspendClient(@RequestParam String dni) throws UserNotExistException {
        try {
            userController.suspendClient(dni);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    // Modificacion del Cliente y la linea telefonica.
    @PutMapping("/")
    public ResponseEntity modifyClient(@RequestBody UserPhoneModifyDto clientPhone) throws UserNotExistException {
        try {
            userController.modifyClient(clientPhone);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (JpaSystemException e) {
            throw new UserNotExistException(e.getCause().getCause().getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws UserNotExistException {
        List<User> clients = userController.getAll();
        if (clients.size() > 0) {
            return ResponseEntity.ok().body(clients);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/all/clients")
    public ResponseEntity<List<ClientsProjection>> getAllClients() throws UserNotExistException {
        List<ClientsProjection> clients = userController.getAllClients();
        if (clients.size() > 0) {
            return ResponseEntity.ok().body(clients);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
