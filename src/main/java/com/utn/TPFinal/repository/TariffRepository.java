package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.Tariff;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Integer> {

    @Query(value = "select * from v_tariffs_info where id_tariff = ?1", nativeQuery = true)
    TariffProjection getTariff(Integer idTariff);
}
