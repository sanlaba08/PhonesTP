package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backoffice/client")
public class ClientBackController {
    private final SessionManager sessionManager;
    private final UserController userController;

    public ClientBackController(SessionManager sessionManager, UserController userController) {
        this.sessionManager = sessionManager;
        this.userController = userController;
    }

    /* 2) Manejo de Clientes (Controller)*/
    // Alta de Cliente con su respectiva linea telefonica.
    @PostMapping("/")
    public ResponseEntity addClient(@RequestHeader("Authorization") String sessionToken, @RequestBody UserPhoneDto clientPhone) throws UserNotExistException{
        userController.addClient(clientPhone);
        return ResponseEntity.ok().build();
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/")
    public ResponseEntity deleteClient(@RequestParam String dni){
        userController.deleteClient(dni);
        return ResponseEntity.ok().build();
    }

    // Modificacion del Cliente y la linea telefonica. (VERIFICAR "NO SOPORTA EL METODO PUT")
    @PutMapping("/")
    public ResponseEntity modifyClient(@RequestBody UserPhoneModifyDto clientPhone){
        userController.modifyClient(clientPhone);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws UserNotExistException{
        List<User> clients = userController.getAll();
        if (clients.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clients);
        }else{
            throw new UserNotExistException("Users not exist.");
        }
    }

    @GetMapping("/all2")
    public ResponseEntity<List<ClientsProjection>> getAllClients() throws UserNotExistException{
        List<ClientsProjection> clients = userController.getAllClients();
        if (clients.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clients);
        }else{
            throw new UserNotExistException("Users not exist.");
        }
    }

    @GetMapping("/number")
    public ResponseEntity getEmployee(@RequestParam String dni) throws UserNotExistException {
        ResponseEntity responseEntity;
        try{
            responseEntity = ResponseEntity.ok(userController.getClient(dni));
        } catch (UserNotExistException e){
            responseEntity = ResponseEntity.badRequest().build();
            throw new UserNotExistException("Dni not exist");
        }
        return responseEntity;
    }
}
