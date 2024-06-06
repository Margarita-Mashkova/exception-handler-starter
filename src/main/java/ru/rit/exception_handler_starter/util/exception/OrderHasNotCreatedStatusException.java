package ru.rit.exception_handler_starter.util.exception;

import java.util.UUID;

public class OrderHasNotCreatedStatusException extends RuntimeException {
    public OrderHasNotCreatedStatusException(UUID id) {
        super(String.format("Impossible to delete or update an order with id [%s], " +
                "because it has already been started or cancelled", id));
    }
}
