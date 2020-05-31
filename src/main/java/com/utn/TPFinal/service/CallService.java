package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.repository.CallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService {
    private final CallRepository callRepository;

    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public List<CallsProjection> getListCall(String dni) {
        return callRepository.getListCall(dni);
    }

    public void addCall(CallDto callDto){
        callRepository.addCall(callDto.getIdLineOrigin(), callDto.getIdLineDestination(), callDto.getDuration(), callDto.getCallDate());
    }
}
