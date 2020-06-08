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
            System.out.println("\n\n\nestoy en clientBackController : ");
        } catch (JpaSystemException e) {
            System.out.println("\n\nfui cacheado\n\n");
            System.out.println(e.getCause().getCause().getMessage());
            throw new UserAllReadyExistException(e.getCause().getCause().getMessage());
        }
        return ResponseEntity.ok().build();
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/")
    public ResponseEntity deleteClient(@RequestParam String dni) throws UserNotExistException{
        try{
            userController.deleteClient(dni);
            return ResponseEntity.ok().build();
        } catch (UserNotExistException e){
            throw new UserNotExistException("User not exist");
        }

    }

    // Modificacion del Cliente y la linea telefonica. (VERIFICAR "NO SOPORTA EL METODO PUT")
    @PutMapping("/")
    public ResponseEntity modifyClient(@RequestBody UserPhoneModifyDto clientPhone) throws UserNotExistException, SQLException {
        try{
            userController.modifyClient(clientPhone);
            return ResponseEntity.ok().build();
        } catch (UserNotExistException e){
            throw new UserNotExistException("User not exist");
        }
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
}
