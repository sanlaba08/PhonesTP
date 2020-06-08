package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.exceptions.CallNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.DestinationCallProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/call")
public class CallWebController {
    private final SessionManager sessionManager;
    private final CallController callController;

    @Autowired
    public CallWebController(SessionManager sessionManager, CallController callController) {
        this.sessionManager = sessionManager;
        this.callController = callController;
    }

    @GetMapping("/destination") // localhost:8080/call/dni?=4123
    public ResponseEntity<List<DestinationCallProjection>> getCallByDestination(@RequestHeader("Authorization") String sessionToken) throws CallNotExistException{
        User session = sessionManager.getCurrentUser(sessionToken);
        List<DestinationCallProjection> callDestination = callController.getCallByDestination(session.getDni());
        if (callDestination.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(callDestination);
        }else{
            throw new CallNotExistException("Call not exist.");
        }
    }

    @GetMapping("/date") // localhost:8080/call/date?first=31-05-2019
    public ResponseEntity <List<CallsProjection>> getCallByDate(@RequestHeader("Authorization") String sessionToken,@RequestParam String first, @RequestParam String second) throws CallNotExistException{
        User session = sessionManager.getCurrentUser(sessionToken);
        List<CallsProjection> callByDate = callController.getCallByDate(session.getDni(),first, second);
        if (callByDate.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(callByDate);
        }else{
            throw new CallNotExistException("Users not exist.");
        }
    }


}
