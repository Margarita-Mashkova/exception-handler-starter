package ru.rit.exception_handler_starter.util.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorDetails {
    private String exceptionClass;
    private String exceptionName;
    private String message;
    private LocalDateTime time;
}
