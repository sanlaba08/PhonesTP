package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.DestinationCallProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/call")
public class CallWebController {
    private final CallController callController;

    @Autowired
    public CallWebController(CallController callController) {
        this.callController = callController;
    }


    @GetMapping("/destination") // localhost:8080/call/dni?=4123
    public DestinationCallProjection getCallByDestination(@RequestParam String dni){
        return callController.getCallByDestination(dni);
    }

    @GetMapping("/date") // localhost:8080/call/date?first=31-05-2019
    public List<CallsProjection> getCallByDestination(@RequestParam String first, @RequestParam String second){
        return callController.getCallByDate(first, second);
    }
}
