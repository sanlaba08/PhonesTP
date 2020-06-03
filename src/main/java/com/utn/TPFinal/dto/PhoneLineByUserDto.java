package com.utn.TPFinal.dto;


public class PhoneLineByUserDto {
    private Integer user;
    private String lineType;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }
}
