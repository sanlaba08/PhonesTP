package com.utn.TPFinal.dto;

import com.utn.TPFinal.model.City;
import com.utn.TPFinal.model.LineType;
import com.utn.TPFinal.model.User;

public class UserPhoneModifyDto {
    Integer user;
    String name;
    String lastName;
    String password;
    Integer city;
    String lineType;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }
}
