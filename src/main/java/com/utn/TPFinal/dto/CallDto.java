package com.utn.TPFinal.dto;

import org.junit.platform.commons.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallDto {
    String lineOrigin;
    String lineDestination;
    Long duration;
    Date callDate;

    public Boolean isValid() {
        return !StringUtils.isBlank(lineOrigin) && !StringUtils.isBlank(lineDestination) && duration != null && duration > 0 && callDate != null;
    }
}
