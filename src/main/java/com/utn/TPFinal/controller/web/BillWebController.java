package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.domain.User;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/bill")
public class BillWebController {
    private final SessionManager sessionManager;
    private final BillController billController;

    @Autowired
    public BillWebController(SessionManager sessionManager, BillController billController) {
        this.sessionManager = sessionManager;
        this.billController = billController;
    }

    //Consulta de facturas por rango de fechas del usuario logeado
    @GetMapping("/date")
    public ResponseEntity <List<BillProjection>> getBillDate(@RequestHeader("Authorization") String sessionToken,
                                                             @RequestParam String from,
                                                             @RequestParam String to) throws ValidationException {
        User session = sessionManager.getCurrentUser(sessionToken);
        List<BillProjection> billsByDate = billController.getBillDate(session.getDni(),from, to);
        if (billsByDate.size() > 0){
             return ResponseEntity.ok().body(billsByDate);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
