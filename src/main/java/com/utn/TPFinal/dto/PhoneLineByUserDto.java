package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneLineByUserDto {
    private Integer user;
    private String lineType;

    public Boolean isValid() {
        return user > 0 && (user!=null) && !StringUtils.isBlank(lineType);
    }
}
