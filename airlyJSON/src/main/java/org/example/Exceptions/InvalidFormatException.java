package org.example.Exceptions;

public class InvalidFormatException extends Exception{
    public InvalidFormatException() {
        super("Station JSON has incorrect field types for 'id' or 'stationName'");
    }
}
