package ru.rit.exception_handler_starter.util.exception;

public class CurrencyServiceUnavailableException extends RuntimeException{
    public CurrencyServiceUnavailableException(String statusCode) {
        super(String.format("The currency server is unavailable. Status code is %s", statusCode));
    }
}
