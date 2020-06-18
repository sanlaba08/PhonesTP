package com.utn.TPFinal.projections;

public interface TariffProjection {
    String getCity_origin();
    String getCity_destination();
    Long getPrice_per_minute();
    Float getCost_per_minute();

    void setCity_origin(String cityOrigin);
    void setCity_destination(String cityDestination);
    void setPrice_per_minute(Long pricePerMinute);
    void setCost_per_minute(Float costPerMinute);
}
