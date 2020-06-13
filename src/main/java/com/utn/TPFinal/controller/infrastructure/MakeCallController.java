package com.utn.TPFinal.controller.infrastructure;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.exceptions.IncorrectDataCallException;
import com.utn.TPFinal.exceptions.IncorrectDataClientPhoneException;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/call")
public class MakeCallController {
    private final CallController callController;

    @Autowired
    public MakeCallController(CallController callController) {
        this.callController = callController;
    }

    @PostMapping("/")
    public ResponseEntity addCall(@RequestBody CallDto callDto) throws IncorrectDataCallException {
        try{
            callController.addCall(callDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (JpaSystemException e){
            throw new IncorrectDataCallException(e.getCause().getCause().getMessage());
        }
    }

}
