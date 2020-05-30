package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.repository.PhoneLineRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneLineService {
    private final PhoneLineRepository phoneLineRepository;

    public PhoneLineService(PhoneLineRepository phoneLineRepository) {
        this.phoneLineRepository = phoneLineRepository;
    }

    public void addPhoneLine(PhoneLineByUserDto phoneLine) {
        phoneLineRepository.addPhoneLine(phoneLine.getUser(), phoneLine.getLineType());
    }
}
