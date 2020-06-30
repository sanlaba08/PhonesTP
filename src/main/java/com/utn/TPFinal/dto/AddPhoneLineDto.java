package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPhoneLineDto {
    private String dni;
    private String lineType;

    public Boolean isValid() {
        return !StringUtils.isBlank(dni) && !StringUtils.isBlank(lineType);
    }

}
