package com.utn.TPFinal.projections;

public interface ClientProjection {
    String getName();
    String getLastName();
    String getDni();
    String getCity();
    String getFullNumber();
    String getLineType();

    void setName(String name);
    void setLastName(String lastName);
    void setDni(String dni);
    void setCity(String city);
    void setFullNumber(String fullNumber);
    void setLineType(String lineType);
}
