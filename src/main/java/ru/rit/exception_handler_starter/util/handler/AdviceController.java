package ru.rit.exception_handler_starter.util.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.rit.exception_handler_starter.util.exception.ArticleAlreadyExistsException;
import ru.rit.exception_handler_starter.util.exception.CategoryNameAlreadyExistsException;
import ru.rit.exception_handler_starter.util.exception.CategoryNotFoundException;
import ru.rit.exception_handler_starter.util.exception.CurrencyServiceUnavailableException;
import ru.rit.exception_handler_starter.util.exception.CustomerLoginAlreadyExistsException;
import ru.rit.exception_handler_starter.util.exception.CustomerNotFoundException;
import ru.rit.exception_handler_starter.util.exception.CustomerOrderNotMatchException;
import ru.rit.exception_handler_starter.util.exception.ErrorDetails;
import ru.rit.exception_handler_starter.util.exception.OrderHasNotCreatedStatusException;
import ru.rit.exception_handler_starter.util.exception.OrderNotFoundException;
import ru.rit.exception_handler_starter.util.exception.ProductInWarehouseNotEnoughException;
import ru.rit.exception_handler_starter.util.exception.ProductNotAvailableException;
import ru.rit.exception_handler_starter.util.exception.ProductNotFoundException;
import ru.rit.exception_handler_starter.util.validation.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Defining a global exception handler
 */
@ControllerAdvice
public class AdviceController {
    /**
     * Handles exceptions of types ProductNotFoundException, CategoryNotFoundException.
     *
     * @param e exception or error object
     * @return a ResponseEntity object with the exception message and an HTTP status code of HttpStatus.BAD_REQUEST.
     */

    @ExceptionHandler({
            ProductNotFoundException.class,
            CategoryNotFoundException.class,
            CustomerNotFoundException.class,
            OrderNotFoundException.class
    })
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ArticleAlreadyExistsException.class,
            CategoryNameAlreadyExistsException.class,
            CustomerLoginAlreadyExistsException.class
    })
    public ResponseEntity<ErrorDetails> handleEntityFieldAlreadyExistsException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductInWarehouseNotEnoughException.class)
    public ResponseEntity<ErrorDetails> handleProductInWarehouseNotEnoughException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<ErrorDetails> handleProductNotAvailableException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerOrderNotMatchException.class)
    public ResponseEntity<ErrorDetails> handleCustomerOrderNotMatchException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OrderHasNotCreatedStatusException.class)
    public ResponseEntity<ErrorDetails> handleOrderHasNotCreatedStatusException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ValidationException.class
    })
    public ResponseEntity<ErrorDetails> handleValidationException(Throwable e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[1].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException e) {
        final Set<ConstraintViolation<?>> bindingResult = e.getConstraintViolations();

        final String errorMessages = bindingResult.stream()
                .map(error -> error.getPropertyPath() + " " + error.getMessage())
                .collect(Collectors.joining("; "));

        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(errorMessages)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Вызывается, если в контроллере указывается @RequestBody или @RequestParam
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        final String errorMessages = fieldErrors.stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(errorMessages)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Вызывается, если в контроллере не указывается @RequestBody или @RequestParam
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDetails> handleBindException(BindException e) {
        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        final String errorMessages = fieldErrors.stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(errorMessages)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorDetails> handleWebClientResponseException(WebClientResponseException e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ErrorDetails> handleWebClientRequestException(WebClientRequestException e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrencyServiceUnavailableException.class)
    public ResponseEntity<ErrorDetails> handleCurrencyServiceUnavailableException(CurrencyServiceUnavailableException e) {
        final ErrorDetails errorDetails = ErrorDetails.builder()
                .exceptionClass(e.getStackTrace()[0].getClassName())
                .exceptionName(e.getClass().getSimpleName())
                .message(e.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
    }

    /**
     * Handles any other exceptions that are not specifically handled. It prints the stack trace of the exception.
     *
     * @param e exception or error object
     * @return a ResponseEntity object with the exception message and an HTTP status code of HttpStatus.INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
