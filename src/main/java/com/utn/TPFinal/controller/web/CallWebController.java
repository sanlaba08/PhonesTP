package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.domain.User;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.session.SessionManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    //Consulta del top 10 de destinos mas llamados del usuario logeado
    @GetMapping("/destination")
    public ResponseEntity<List<CallsProjection>> getTopTenDestinations(@RequestHeader("Authorization") String sessionToken) throws ValidationException {
        User session = sessionManager.getCurrentUser(sessionToken);
        List<CallsProjection> callDestination = callController.getTopTenDestinations(session.getDni());
        if (callDestination.size() > 0) {
            return ResponseEntity.ok().body(callDestination);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //Consulta del llamadas por rango de fecha del usuario logeado
    @GetMapping("/date") // localhost:8080/call/date?first=31-05-2019
    @ApiOperation(value = "Resturn a list of calls filtered by dates")
    public ResponseEntity<List<CallsProjection>> getCallByDate(@RequestHeader("Authorization") String sessionToken,
                                                               @RequestParam String from,
                                                               @RequestParam String to) throws ValidationException {

        User session = sessionManager.getCurrentUser(sessionToken);
        List<CallsProjection> callByDate = callController.getCallByDate(session.getDni(), from, to);
        if (callByDate.size() > 0) {
            return ResponseEntity.ok().body(callByDate);
        } else {
            return ResponseEntity.noContent().build();
        }

    }
}
