package org.example.Exceptions;

public class MissingRequiredFieldException extends Exception{
    public MissingRequiredFieldException() {
        super("Station JSON is missing required fields: 'id' or 'stationName'");
    }
}
