package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.exceptions.BillNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.ClientProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/backoffice/bill")
public class BillBackController {
    private final BillController billController;
    private final PhoneLineController phoneLineController;

    public BillBackController(BillController billController, PhoneLineController phoneLineController) {
        this.billController = billController;
        this.phoneLineController = phoneLineController;
    }

    @GetMapping("/number") // localhost:8080/bill/number?line=4123
    public ResponseEntity<List<BillProjection>> getBillbyNumber(@RequestParam String line) {
        ClientProjection clientLine = phoneLineController.getClientLine(line);
        if (clientLine == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<BillProjection> billsByNumber = billController.getBillByNumber(line);
            if (billsByNumber.size() > 0) {
                return ResponseEntity.ok().body(billsByNumber);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<BillProjection>> getBillAll() {
        List<BillProjection> bills = billController.getBillAll();
        if (bills.size() > 0) {
            return ResponseEntity.ok().body(bills);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}