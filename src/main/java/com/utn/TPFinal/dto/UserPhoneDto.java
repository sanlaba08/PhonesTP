package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneDto {
    String name;
    String lastName;
    String dni;
    String password;
    Integer city;
    String lineType;

    public Boolean isValid() {
        return !StringUtils.isBlank(name) && !StringUtils.isBlank(lastName) && !StringUtils.isBlank(dni) && !StringUtils.isBlank(password) && !StringUtils.isBlank(lineType) && city > 0 && city !=null ;
    }

}
