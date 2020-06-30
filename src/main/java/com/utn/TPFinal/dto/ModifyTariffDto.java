package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyTariffDto {
    Integer idTariff;
    long pricePerMinute;
    float costPerMinute;

    public Boolean isValid() {
        return idTariff > 0 && pricePerMinute > 0 && costPerMinute > 0;
    }
}
