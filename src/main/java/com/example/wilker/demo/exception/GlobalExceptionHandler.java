package com.example.wilker.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDetalhes> handleRuntime(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroDetalhes(HttpStatus.BAD_REQUEST.name(), exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
