package com.utn.TPFinal.service;

import com.utn.TPFinal.model.LineType;
import com.utn.TPFinal.repository.LineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineTypeService {
    private final LineTypeRepository lineTypeRepository;

    @Autowired
    public LineTypeService(LineTypeRepository lineTypeRepository) {
        this.lineTypeRepository = lineTypeRepository;
    }


    public List<LineType> getAll() {
        return lineTypeRepository.findAll();
    }

    public void add(final LineType newLineType) {
        lineTypeRepository.save(newLineType);
    }
}
