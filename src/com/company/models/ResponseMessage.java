package com.company.models;

import java.time.LocalDateTime;
import java.util.Date;

public class ResponseMessage {

    private Date time;

    private String message;

    public ResponseMessage() {
        time = new Date();
    }

    public ResponseMessage(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
