package com.company.exception.handler;

import com.company.exception.WrongIdException;
import com.company.models.ResponseMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = com.company.annotations.StoreExceptionHandler.class)
public class StoreExceptionHandler {

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<?> handleWrongIdException(WrongIdException exception){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(String.format("Store with id %d was not found.",exception.getId()));
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

}
