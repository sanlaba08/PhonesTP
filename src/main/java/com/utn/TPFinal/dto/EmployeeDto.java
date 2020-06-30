package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    String name;
    String lastName;
    String dni;
    String password;
    Integer city;

    public Boolean isValid() {
        return !StringUtils.isBlank(name) && !StringUtils.isBlank(lastName) && !StringUtils.isBlank(dni) && !StringUtils.isBlank(password) && city != null && city > 0;
    }
}
