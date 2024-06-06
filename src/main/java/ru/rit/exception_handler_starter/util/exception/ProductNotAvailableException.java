package ru.rit.exception_handler_starter.util.exception;

import java.util.UUID;

public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(UUID id) {
        super(String.format("Product with id [%s] is NOT available for order", id));
    }
}
