package com.utn.TPFinal.projections;

public interface TariffProjection {
    String getCity_origin();
    String getCity_destination();
    Long getPrice_per_minute();
    Float getCost_per_minute();
}
