package com.utn.TPFinal.dto;

import java.util.Date;

public class CallDto {
    String lineOrigin;
    String lineDestination;
    Long duration;
    Date callDate;

    public String getLineOrigin() {
        return lineOrigin;
    }

    public void setLineOrigin(String lineOrigin) {
        this.lineOrigin = lineOrigin;
    }

    public String getLineDestination() {
        return lineDestination;
    }

    public void setLineDestination(String lineDestination) {
        this.lineDestination = lineDestination;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }
}
