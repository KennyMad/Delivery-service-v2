package com.company.exception;

public class InvalidAttributeException extends Exception{

    private final String attribute;

    public InvalidAttributeException (String attribute){
        super("Attribute " + attribute + " does not exist.");
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
