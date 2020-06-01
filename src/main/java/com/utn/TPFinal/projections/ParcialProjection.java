package com.utn.TPFinal.projections;

/* Realizar un endpoint que muestre el nombre, apellido y los destinos mas llamados por el usuario */
public interface ParcialProjection {
    String getFull_name();
    String getDestination_city();
    Integer getCant();
}
