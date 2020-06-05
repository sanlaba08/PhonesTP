package com.utn.TPFinal.controller.infrastructure;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.dto.CallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class MakeCallController {
    private final CallController callController;

    @Autowired
    public MakeCallController(CallController callController) {
        this.callController = callController;
    }

    @PostMapping("/")
    public void addCall(@RequestBody CallDto callDto){
        callController.addCall(callDto);
    }

}
