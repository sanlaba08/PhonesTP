package com.utn.TPFinal.service;


import com.utn.TPFinal.model.Province;
import com.utn.TPFinal.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }


    public List<Province> getProvinceAll() {
        return provinceRepository.findAll();
    }
}
