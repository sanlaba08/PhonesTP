package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backoffice/client")
public class ClientBackController {
    private final UserController userController;

    @Autowired
    public ClientBackController(UserController userController) {
        this.userController = userController;
    }

    /* 2) Manejo de Clientes (Controller)*/
    // Alta de Cliente con su respectiva linea telefonica.
    @PostMapping("/")
    public void addClient(@RequestBody UserPhoneDto clientPhone) throws UserNotExistException{
        userController.addClient(clientPhone);
    }

    // Baja de Cliente con su respectiva linea telefonica.
    @DeleteMapping("/")
    public void deleteClient(@RequestParam String dni){
        userController.deleteClient(dni);
    }

    // Modificacion del Cliente y la linea telefonica. (VERIFICAR "NO SOPORTA EL METODO PUT")
    @PutMapping("/")
    public void modifyClient(@RequestBody UserPhoneModifyDto clientPhone){
        userController.modifyClient(clientPhone);
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
