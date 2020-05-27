package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.LineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineTypeRepository extends JpaRepository<LineType, Integer> {

}
