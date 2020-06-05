package com.utn.TPFinal.controller.web;

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
@RequestMapping("/web/bill")
public class BillWebController {
    private final BillController billController;

    @Autowired
    public BillWebController(BillController billController) {
        this.billController = billController;
    }

    @GetMapping("/date")
    public ResponseEntity <List<BillProjection>> getBillDate(@RequestParam String first, @RequestParam String second) throws BillNotExistException{
        List<BillProjection> billsByDate = billController.getBillDate(first, second);
        if (billsByDate.size() > 0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(billsByDate);
        }else{
            throw new BillNotExistException("Bills not exist.");
        }
    }
}
