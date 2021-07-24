package com.company.exception;

public class LoadDataException extends Exception{

    private final String fileName;

    public LoadDataException(String fileName, String message){
        super("Failed to load file: " + fileName + " ("+message +")");
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
