package com.utn.TPFinal.projections;

/* Realizar un endpoint que muestre el nombre, apellido y los destinos mas llamados por el usuario */
public interface DestinationCallProjection {
    String getFullName();
    String getDestination_city();
    Integer getCant();
}
