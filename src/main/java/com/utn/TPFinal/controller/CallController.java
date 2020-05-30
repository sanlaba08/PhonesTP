package com.utn.TPFinal.controller;

import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
import com.utn.TPFinal.service.CallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/") // localhost:8080/call/dni?=4123
    public List<CallsProjection> getCall(@RequestParam String dni){
        return callService.getListCall(dni);
    }
}
