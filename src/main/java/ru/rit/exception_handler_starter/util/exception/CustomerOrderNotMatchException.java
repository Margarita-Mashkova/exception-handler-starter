package ru.rit.exception_handler_starter.util.exception;

public class CustomerOrderNotMatchException extends RuntimeException {
    // Стоит ли тут передавать id, конечно, с точки зрения конфиденциальности...
    public CustomerOrderNotMatchException(Long id) {
        super(String.format("The customer doesn't have access to this order. Order belongs to customer with id [%d]", id));
    }
}
