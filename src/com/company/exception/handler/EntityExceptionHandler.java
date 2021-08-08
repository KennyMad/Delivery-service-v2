package com.company.exception.handler;

import com.company.exception.InvalidAttributeException;
import com.company.exception.WrongIdException;
import com.company.models.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handleWrongIdException(WrongIdException exception){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(String.format("Entity with id %d was not found.",exception.getId()));
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseMessage> handleInvalidAttributeException(InvalidAttributeException exception){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(String.format("Invalid attribute %s",exception.getAttribute()));
        return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }

}
