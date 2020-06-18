package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.repository.BillRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BillBackControllerTest {

    BillBackController billBackController;

    @Mock
    BillController billController;

    @Before
    public void setUp() {
        initMocks(this);
        billBackController = new BillBackController(billController);
    }

    @Test
    void getBillAllOk() throws SQLException {
//        BillProjection projection = new BillProjection();

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        BillProjection billProjection = factory.createProjection(BillProjection.class);

        billProjection.setComplete_name("Palo Kell");
        billProjection.setDni("42231235");
        billProjection.setFull_number("02236493784");
        billProjection.setCalls_quantity(1);
        billProjection.setTotal_price(500);
        billProjection.setTotal_cost(200);
        billProjection.setBill_date(new Date(2020,06,17));//CREEMOS Q ESTAMOS CARGANDO MAL LA FECHA
        billProjection.setExpiration_date(new Date(2020, 07, 02));
        billProjection.setPaid(false);


        System.out.println(
                        billProjection.getComplete_name() + " \n" +
                        billProjection.getDni() + " \n" +
                        billProjection.getFull_number() + " \n" +
                        billProjection.getCalls_quantity() + " \n" +
                        billProjection.getTotal_price() + " \n" +
                        billProjection.getTotal_cost() + " \n" +
                        billProjection.getBill_date() + " \n" +
                        billProjection.getExpiration_date() + " \n" +
                        billProjection.getPaid() + " \n");

        List<BillProjection> bills = new ArrayList<BillProjection>();
        bills.add(billProjection);

        Mockito.when(billController.getBillAll()).thenReturn(bills);
    }

    @Test
    void getBillAll() {
    }
}