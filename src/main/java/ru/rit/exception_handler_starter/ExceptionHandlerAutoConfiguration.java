package ru.rit.exception_handler_starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rit.exception_handler_starter.util.handler.AdviceController;

@Configuration
public class ExceptionHandlerAutoConfiguration {
    @Bean
    public AdviceController adviceController(){
        return new AdviceController();
    }
}
