package ru.rit.exception_handler_starter.util.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID id) {
        super(String.format("Order with id [%s] not found", id));
    }
}
