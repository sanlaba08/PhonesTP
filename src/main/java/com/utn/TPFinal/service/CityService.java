package com.utn.TPFinal.service;

import com.utn.TPFinal.model.City;
import com.utn.TPFinal.repository.CityRepository;
import com.utn.TPFinal.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCityAll() {
        return cityRepository.findAll();
    }
}
