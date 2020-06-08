package com.utn.TPFinal.controller.infrastructure;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.exceptions.IncorrectDataCallException;
import com.utn.TPFinal.exceptions.IncorrectDataClientPhoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void addCall(@RequestBody CallDto callDto) throws IncorrectDataCallException, SQLException{
        try{
            callController.addCall(callDto);
        } catch (SQLException e){
            throw new IncorrectDataCallException(e.getMessage());
        }
    }

}
