package com.company.exception;

public class WrongIdException extends Exception{

    private final int id;

    public WrongIdException(int id){
        super("Object with id " + id + " was not found.");

        this.id = id;
    }

    public int getId() {
        return id;
    }
}
