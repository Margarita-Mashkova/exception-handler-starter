package ru.rit.exception_handler_starter.util.exception;

public class CategoryNameAlreadyExistsException extends RuntimeException {
    public CategoryNameAlreadyExistsException(String name) {
        super(String.format("Category with name [%s] already exists", name));
    }
}
