package com.example.wilker.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String cpf;
}
