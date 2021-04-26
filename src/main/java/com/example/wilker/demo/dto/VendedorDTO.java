package com.example.wilker.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendedorDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private List<Integer> clientes;
}
