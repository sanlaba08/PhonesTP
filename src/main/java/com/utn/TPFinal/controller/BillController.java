package com.utn.TPFinal.controller;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/number") // localhost:8080/bill/number?line=4123
    public List<BillProjection> getCall(@RequestParam String line){
        return billService.getBillByNumber(line);
    }

    @GetMapping("/") // localhost:8080/call/number?=4123
    public List<BillProjection> getCallAll(){
        return billService.getBillAll();
    }
}
