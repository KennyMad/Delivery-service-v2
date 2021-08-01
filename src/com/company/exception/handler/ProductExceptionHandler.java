package com.company.exception.handler;

import com.company.exception.InvalidAttributeException;
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
@ControllerAdvice(annotations = com.company.annotations.ProductExceptionHandler.class)
public class ProductExceptionHandler {

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<?> handleWrongIdException(WrongIdException exception){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(String.format("Product with id %d was not found.",exception.getId()));
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<?> handleInvalidAttributeException(InvalidAttributeException exception){
        return new ResponseEntity<>(String.format("Invalid product attribute %s",exception.getAttribute()),HttpStatus.BAD_REQUEST);
    }

}
