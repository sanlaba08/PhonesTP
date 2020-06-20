package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.repository.BillRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BillBackControllerTest {

    private BillBackController billBackController;
    private BillProjection billProjection;

    @Mock
    private BillController billController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        billBackController = new BillBackController(billController);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        billProjection = factory.createProjection(BillProjection.class);
    }

    @Test
    void getBillAllOk(){
       billProjection.setComplete_name("Santiago Labatut");
       billProjection.setDni("41686701");
       billProjection.setFull_number("02235351545");
       billProjection.setCalls_quantity(5);
       billProjection.setTotal_price((long) 300);
       billProjection.setTotal_cost((long) 500);
       billProjection.setBill_date(new Date());
       billProjection.setExpiration_date(new Date());
       billProjection.setPaid(false);

        List<BillProjection> bills = new ArrayList<BillProjection>();
        bills.add(billProjection);

        when(billController.getBillAll()).thenReturn(bills);

        ResponseEntity<List<BillProjection>> response = billBackController.getBillAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bills, response.getBody());
    }

    @Test
    void getAllBillEmpty() {
        List<BillProjection> billList = new ArrayList<BillProjection>();

        when(billController.getBillAll()).thenReturn(billList);
        ResponseEntity<List<BillProjection>> response = billBackController.getBillAll();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getBillbyNumberOk() {
        billProjection.setComplete_name("Santiago Labatut");
        billProjection.setDni("41686701");
        billProjection.setFull_number("02235351545");
        billProjection.setCalls_quantity(5);
        billProjection.setTotal_price((long) 300);
        billProjection.setTotal_cost((long) 500);
        billProjection.setBill_date(new Date());
        billProjection.setExpiration_date(new Date());
        billProjection.setPaid(false);

        List<BillProjection> bills = new ArrayList<BillProjection>();
        bills.add(billProjection);

        when(billController.getBillByNumber("02235351545")).thenReturn(bills);

        ResponseEntity<List<BillProjection>> response = billBackController.getBillbyNumber("02235351545");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bills, response.getBody());
    }

    @Test
    void getBillByNumberEmpty() {
        List<BillProjection> billList = new ArrayList<BillProjection>();

        when(billController.getBillByNumber("02236162410")).thenReturn(billList);
        ResponseEntity<List<BillProjection>> response = billBackController.getBillbyNumber("02236162410");

        assertEquals(204, response.getStatusCodeValue());
    }
}