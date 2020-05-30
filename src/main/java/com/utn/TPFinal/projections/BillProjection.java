package com.utn.TPFinal.projections;


import java.util.Date;

public interface BillProjection {
    String getComplete_name();
    String getFull_number();
    Integer getCalls_quantity();
    Long getTotal_price();
    Long getTotal_cost();
    Date getBill_date();
    Date getExpiration_date();


}

