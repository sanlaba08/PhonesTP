package com.utn.TPFinal.proyections;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Clients {
    String getId_user();
    String getRol();
    String getName();
    String getLast_name();
    String getDni();
    String getPassword();
    String getCity();
    String getProvince();
    String getFull_number();
    String getLine_Type();
}
