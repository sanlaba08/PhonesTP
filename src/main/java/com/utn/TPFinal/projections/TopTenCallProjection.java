package com.utn.TPFinal.projections;

public interface TopTenCallProjection {
    String getFull_name_o();
    String getDestination_city();
    Integer getCant();

    void setFull_name_o(String fullNameO);
    void setDestination_city(String destinationCity);
    void setCant(Integer cant);
}
