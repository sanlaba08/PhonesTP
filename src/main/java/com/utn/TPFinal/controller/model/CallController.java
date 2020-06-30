package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.service.CallService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    public List<CallsProjection> getCall(String dni) throws ValidationException {
        if (!StringUtils.isBlank(dni)) {
            return callService.getListCall(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public Integer addCall(CallDto callDto) throws ValidationException {
        if (callDto.isValid()) {
            return callService.addCall(callDto);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public List<CallsProjection> getTopTenDestinations(String dni) throws ValidationException {
        if (!StringUtils.isBlank(dni)) {
            return callService.getTopTenDestinations(dni);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public List<CallsProjection> getCallByDate(String dni, String firstDate, String secondDate) throws ValidationException {
        if (!StringUtils.isBlank(dni) && !StringUtils.isBlank(firstDate) && !StringUtils.isBlank(secondDate) && firstDate.compareTo(secondDate) < 0) {
            return callService.getListCallByDate(dni,firstDate, secondDate);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }
}
