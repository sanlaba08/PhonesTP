package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.repository.PhoneLineRepository;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
public class PhoneLineService {
    private final PhoneLineRepository phoneLineRepository;

    public PhoneLineService(PhoneLineRepository phoneLineRepository) {
        this.phoneLineRepository = phoneLineRepository;
    }

    public void addPhoneLine(PhoneLineByUserDto phoneLine) throws JpaSystemException {
        phoneLineRepository.addPhoneLine(phoneLine.getUser(), phoneLine.getLineType());
    }

    public void deletePhoneLine(Integer idLine) throws JpaSystemException {
        phoneLineRepository.deletePhoneLine(idLine);
    }

    public void enablePhoneLine(Integer idLine) throws JpaSystemException {
        phoneLineRepository.enablePhoneLine(idLine);
    }
}
