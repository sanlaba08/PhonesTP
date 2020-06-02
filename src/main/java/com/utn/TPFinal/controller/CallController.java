package com.utn.TPFinal.controller;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.DestinationCallProjection;
import com.utn.TPFinal.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/call")
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/") // localhost:8080/call/dni?=4123
    public List<CallsProjection> getCall(@RequestParam String dni){
        return callService.getListCall(dni);
    }

    @PostMapping("/")
    public void addCall(@RequestBody CallDto callDto){
            callService.addCall(callDto);
    }

    @GetMapping("/destination") // localhost:8080/call/dni?=4123
    public DestinationCallProjection getCallByDestination(@RequestParam String dni){
        return callService.getCallByDestination(dni);
    }

    @GetMapping("/date") // localhost:8080/call/date?first=31-05-2019
    public List<CallsProjection> getCallByDestination(@RequestParam String first, @RequestParam String second){
        return callService.getListCallByDate(first, second);
    }
}
