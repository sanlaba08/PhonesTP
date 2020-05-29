package com.utn.TPFinal.dto;

import com.utn.TPFinal.model.City;
import com.utn.TPFinal.model.LineType;
import com.utn.TPFinal.model.User;

public class UserPhoneModifyDto {
    User user;
    String name;
    String lastName;
    String password;
    City city;
    LineType lineType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }
}
