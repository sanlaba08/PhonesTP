package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.exceptions.BillNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @GetMapping("/date")
    public ResponseEntity <List<BillProjection>> getBillDate(@RequestHeader("Authorization") String sessionToken,
                                                             @RequestParam String first,
                                                             @RequestParam String second) throws BillNotExistException, SQLException {
        User session = sessionManager.getCurrentUser(sessionToken);
        List<BillProjection> billsByDate = billController.getBillDate(session.getDni(),first, second);
        if (billsByDate.size() > 0){
             return ResponseEntity.ok().body(billsByDate);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
