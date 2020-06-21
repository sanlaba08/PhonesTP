package com.utn.TPFinal.dto;

import com.utn.TPFinal.model.City;
import com.utn.TPFinal.model.LineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
