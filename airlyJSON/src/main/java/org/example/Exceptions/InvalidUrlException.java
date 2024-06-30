package org.example.Exceptions;

public class InvalidUrlException extends Exception{
    public InvalidUrlException() {
        super("JSON url is not valid, please check it again");
    }
}
