package com.example.wilker.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDetalhes {
    private String error;
    private String message;
    private Integer statusCode;
}
