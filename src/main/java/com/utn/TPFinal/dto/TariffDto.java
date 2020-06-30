package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TariffDto {
    Integer idOriginCity;
    Integer idDestinationCity;
    long pricePerMinute;
    float costPerMinute;

    public Boolean isValid() {
        return idOriginCity != null && idOriginCity > 0 && idDestinationCity!=null && idDestinationCity>0 &&  pricePerMinute > 0 && costPerMinute > 0;
    }
}