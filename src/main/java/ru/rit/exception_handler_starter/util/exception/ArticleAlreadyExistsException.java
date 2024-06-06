package ru.rit.exception_handler_starter.util.exception;

import java.util.UUID;

public class ArticleAlreadyExistsException extends RuntimeException {

    public ArticleAlreadyExistsException(String article, UUID id) {
        super(String.format("Product with article [%s] already exists. Existing product id = %s", article, id));
    }
}
