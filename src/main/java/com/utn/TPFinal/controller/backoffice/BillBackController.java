package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.exceptions.BillNotExistException;
import com.utn.TPFinal.projections.BillProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backoffice/bill")
public class BillBackController {
    private final BillController billController;

    @Autowired
    public BillBackController(BillController billController) {
        this.billController = billController;
    }

    @GetMapping("/number") // localhost:8080/bill/number?line=4123
    public ResponseEntity <List<BillProjection>> getBill(@RequestParam String line) throws BillNotExistException{
        List<BillProjection> billsByNumber = billController.getBill(line);
        if (billsByNumber.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(billsByNumber);
        }else{
            throw new BillNotExistException("Bills not exist.");
        }
    }

    @GetMapping("/")
    public ResponseEntity <List<BillProjection>> getBillAll() throws BillNotExistException{
        List<BillProjection> bills = billController.getBillAll();
        if (bills.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(bills);
        }else{
            throw new BillNotExistException("Bills not exist.");
        }

    }


}
