package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.AddPhoneLineDto;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.ClientProjection;
import com.utn.TPFinal.service.PhoneLineService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

@Controller
public class PhoneLineController {
    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    public Integer addPhoneLine(PhoneLineByUserDto phoneLine) throws JpaSystemException, ValidationException {
        if (phoneLine.isValid()) {
            return phoneLineService.addPhoneLine(phoneLine);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public Integer addPhoneLineByDni(AddPhoneLineDto phoneLine) throws JpaSystemException, ValidationException {
        if (phoneLine.isValid()) {
            return phoneLineService.addPhoneLineByDni(phoneLine);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void suspendPhoneLine(Integer idLine) throws JpaSystemException, ValidationException {
        if (idLine != null && idLine > 0) {
            phoneLineService.suspendPhoneLine(idLine);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public void enablePhoneLine(Integer idLine) throws JpaSystemException, ValidationException {
        if (idLine != null && idLine > 0) {
            phoneLineService.enablePhoneLine(idLine);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public ClientProjection getClientLine(String line) throws ValidationException {
        if (!StringUtils.isBlank(line)) {
            return phoneLineService.getClientLine(line);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }
}
