package com.company.exception;

public class SaveDataException extends Exception{
    public SaveDataException(String message){
        super("Failed to save file: " + message);
    }
}
