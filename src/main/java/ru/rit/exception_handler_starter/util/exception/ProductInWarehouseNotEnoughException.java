package ru.rit.exception_handler_starter.util.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductInWarehouseNotEnoughException extends RuntimeException {
    public ProductInWarehouseNotEnoughException(UUID id, BigDecimal requestedAmount, BigDecimal availableAmount) {
        super(String.format("Product with id [%s] not enough in warehouse. Requested amount: [%s]. " +
                "Available amount: [%s].", id, requestedAmount, availableAmount));
    }
}
