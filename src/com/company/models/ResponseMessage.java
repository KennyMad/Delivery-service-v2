package com.company.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime time;

    private String message;

    public ResponseMessage() {
        time = LocalDateTime.now();
    }

    public ResponseMessage(LocalDateTime time) {
        this.time = time;
    }
}
