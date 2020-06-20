package com.utn.TPFinal.projections;

import java.util.Date;

public interface CallsProjection {
    String getFull_name_o();
    String getDni_user_origin();
    String getOrigin_line();
    String getOrigin_province();
    String getOrigin_city();
    String getFull_name_d();
    String getDni_user_destination();
    String getDestination_line();
    String getDestination_city();
    Long getDuration();
    Integer getTotal_price();
    Float getTotal_cost();
    Date getCall_date();


    void getFull_name_o(String Full_name_o);
    void getDni_user_origin(String Dni_user_origin);
    void setOrigin_line(String originLine);
    void getOrigin_province(String Origin_province);
    void setOrigin_city(String originCity);
    void getFull_name_d(String Full_name_d);
    void getDni_user_destination(String Dni_user_destination);
    void setDestination_line(String destinationLine);
    void setDestination_city(String destination_city);
    void setDuration(Long duration);
    void setTotal_price(Integer totalPrice);
    void setTotal_cost(Float totalCost);
    void setCall_date(Date callDate);


}