package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine, Integer> {
    @Procedure(procedureName = "sp_phone_lines")
    void addPhoneLine(@Param("pIdUser") Integer idUser, @Param("pLineType") String TypeLine);
}
