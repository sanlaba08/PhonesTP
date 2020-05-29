package com.utn.TPFinal.dto;

import com.utn.TPFinal.model.LineType;
import com.utn.TPFinal.model.User;

public class PhoneLineByUserDto {
    User user;
    LineType lineType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }
}
