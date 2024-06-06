package ru.rit.exception_handler_starter.util.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Customer with id [%d] not found", id));
    }
}
