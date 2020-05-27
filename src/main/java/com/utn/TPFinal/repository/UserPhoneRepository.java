package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.City;
import com.utn.TPFinal.model.LineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserPhoneRepository {

    @Procedure(procedureName = "sp_insert_client_and_phone_lines")
    public void addEmployee(@Param("pName") String name, @Param("pLastName") String lastName, @Param("pDni") String dni, @Param("pUserPassword") String password, @Param("pIdCity") City idCity, @Param("pLineType") LineType pLineType);


}
