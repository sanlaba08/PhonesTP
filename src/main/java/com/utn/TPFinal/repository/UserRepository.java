package com.utn.TPFinal.repository;

import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.ClientsProjection;
import com.utn.TPFinal.projections.EmployeesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure(procedureName = "sp_insert_employee")
    Integer addEmployee(@Param("pName") String name,
                     @Param("pLastName") String lastName,
                     @Param("pDni") String dni,
                     @Param("pUserPassword") String password,
                     @Param("pIdCity") Integer idCity) throws JpaSystemException;

    @Query(value = "select * from v_employee_info;", nativeQuery = true)
    List<EmployeesProjection> getEmployee();

    @Query(value = "select * from v_client_info;", nativeQuery = true)
    List<ClientsProjection> getClients();

    @Transactional
    @Procedure(procedureName = "sp_insert_client_and_phone_lines")
    Integer addClientPhone(@Param("pName") String name,
                        @Param("pLastName") String lastName,
                        @Param("pDni") String dni,
                        @Param("pUserPassword") String password,
                        @Param("pIdCity") Integer idCity,
                        @Param("pLineType") String lineType) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_delete_user")
    void deleteClientPhone(@Param("pDni") String dni) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_client_line_suspend")
    void suspendClient(@Param("pDni") String dni) throws UserNotExistException;

    @Transactional
    @Procedure(procedureName = "sp_modify_client")
    void modifyClientPhone(@Param("pIdUser") Integer idUser,
                           @Param("pName") String name,
                           @Param("pLastName") String lastName,
                           @Param("pDni") String dni,
                           @Param("pPassword") String password,
                           @Param("pIdCity") Integer idCity,
                           @Param("pLineType") String lineType) throws JpaSystemException;

    @Query(value = "select * from v_employee_info where dni = ?1", nativeQuery = true)
    EmployeesProjection getEmployeeDni(String dni);

    @Query(value = "SELECT * FROM users u WHERE u.dni = ? and u.user_password = ?", nativeQuery = true)
    User getByUsername(@Param("dni") String dni, @Param("user_password") String password);

    @Query(value = "select * from v_client_info where dni = ?1", nativeQuery = true)
    List<ClientsProjection> getClientDni(String dni);

    @Transactional
    @Procedure(procedureName = "sp_client_reactive")
    void reactiveClient(@Param("pDni") String dni) throws UserNotExistException;
}
