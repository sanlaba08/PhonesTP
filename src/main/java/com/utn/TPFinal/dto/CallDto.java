package com.utn.TPFinal.dto;

import java.util.Date;

public class CallDto {
    Integer idLineOrigin;
    Integer idLineDestination;
    Long duration;
    Date callDate;

    public Integer getIdLineOrigin() {
        return idLineOrigin;
    }

    public void setIdLineOrigin(Integer idLineOrigin) {
        this.idLineOrigin = idLineOrigin;
    }

    public Integer getIdLineDestination() {
        return idLineDestination;
    }

    public void setIdLineDestination(Integer idLineDestination) {
        this.idLineDestination = idLineDestination;
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
