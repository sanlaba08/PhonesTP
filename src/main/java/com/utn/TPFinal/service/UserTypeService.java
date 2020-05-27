package com.utn.TPFinal.service;

import com.utn.TPFinal.model.UserType;
import com.utn.TPFinal.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }


    public List<UserType> getUserTypeAll() {
        return userTypeRepository.findAll();
    }


}
