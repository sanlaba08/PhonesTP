package com.utn.TPFinal.dto;


public class PhoneLineByUserDto {
    private Integer user;
    private Integer lineType;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getLineType() {
        return lineType;
    }

    public void setLineType(Integer lineType) {
        this.lineType = lineType;
    }
}
