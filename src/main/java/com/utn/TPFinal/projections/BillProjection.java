package com.utn.TPFinal.projections;


import java.util.Date;

public interface BillProjection {

    String getComplete_name();
    String getDni();
    String getFull_number();
    Integer getCalls_quantity();
    Long getTotal_price();
    Long getTotal_cost();
    Date getBill_date();
    Date getExpiration_date();
    boolean getPaid();

    //esto es para el test
    void setComplete_name(String name);
    void setDni(String dni);
    void setFull_number(String number);
    void setCalls_quantity(Integer quantity);
    void setTotal_price(long total_price);
    void setTotal_cost(long total_cost);
    void setBill_date(Date billDate);
    void setExpiration_date(Date expirationDate);
    void setPaid(boolean paid);

}

