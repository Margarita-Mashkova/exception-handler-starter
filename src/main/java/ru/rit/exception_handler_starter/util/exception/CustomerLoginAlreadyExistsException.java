package ru.rit.exception_handler_starter.util.exception;

public class CustomerLoginAlreadyExistsException extends RuntimeException {
    public CustomerLoginAlreadyExistsException(String login, Long id) {
        super(String.format("Customer with login [%s] already exists. Existing customer id = %s", login, id));
    }
}
