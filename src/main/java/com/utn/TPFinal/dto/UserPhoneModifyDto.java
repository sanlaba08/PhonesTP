package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneModifyDto {
    Integer user;
    String name;
    String lastName;
    String dni;
    String password;
    Integer city;

    public Boolean isValid() {
        return user > 0 && user != null && !StringUtils.isBlank(lastName) && !StringUtils.isBlank(dni) && !StringUtils.isBlank(password) && city > 0  ;
    }
}
