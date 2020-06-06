package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.DestinationCallProjection;
import com.utn.TPFinal.repository.CallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {
    private final CallRepository callRepository;

    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public List<CallsProjection> getListCall(String dni) {
        return callRepository.getListCall(dni);
    }

    public void addCall(CallDto callDto) {
        callRepository.addCall(callDto.getLineOrigin(), callDto.getLineDestination(), callDto.getDuration(), callDto.getCallDate());
    }

    public List<DestinationCallProjection> getCallByDestination(String dni){
        return callRepository.getCallByDestination(dni);
    }

    public List<CallsProjection> getListCallByDate(String dni,String firstDate, String secondDate){
        return callRepository.getListCallByDate(dni,firstDate, secondDate);
    }
}
