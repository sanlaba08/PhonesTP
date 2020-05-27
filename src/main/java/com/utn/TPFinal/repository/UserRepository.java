package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.User;
import com.utn.TPFinal.proyections.Clients;
import com.utn.TPFinal.proyections.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure(procedureName = "sp_insert_employee")
    public void addEmployee(@Param("pName") String name, @Param("pLastName") String lastName, @Param("pDni") String dni, @Param("pUserPassword") String password, @Param("pIdCity") Integer idCity);

    @Query(value= "select * from v_employee_info;" , nativeQuery = true)
    List<Employees> getEmployee();

    @Query(value= "select * from v_client_info;" , nativeQuery = true)
    List<Clients> getClients();

    @Query(value = "select * from v_client_info where id_user = ?1", nativeQuery = true)
    List<Clients> getClient(Integer idUser);

    @Query(value = "select * from v_employee_info where id_user = ?1", nativeQuery = true)
    List<Employees> getEmployee(Integer idUser);

}
