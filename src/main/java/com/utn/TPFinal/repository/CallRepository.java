package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.Call;
import com.utn.TPFinal.projections.CallsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query(value = "select *" + " from v_calls_info where dni_user_origin = ?1", nativeQuery = true)
    List<CallsProjection> getListCall(String dni);

}
