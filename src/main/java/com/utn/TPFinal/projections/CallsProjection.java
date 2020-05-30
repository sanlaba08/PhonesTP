package com.utn.TPFinal.projections;

import java.util.Date;

public interface CallsProjection {
    String getOrigin_line();
    String getOrigin_city();
    String getDestination_line();
    String getDestination_city();
    Long getDuration();
    Integer getTotal_price();
    Integer getTotal_cost();
    Date getCall_date();

}
