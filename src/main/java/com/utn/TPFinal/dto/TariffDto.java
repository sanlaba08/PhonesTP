package com.utn.TPFinal.dto;

import lombok.Data;

@Data
public class TariffDto {
    String originCityName;
    String destinationCityName;
    long pricePerMinute;
    float costPerMinute;
}