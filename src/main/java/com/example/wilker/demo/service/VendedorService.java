package com.example.wilker.demo.service;

import com.example.wilker.demo.dto.VendedorDTO;
import com.example.wilker.demo.entity.Cliente;
import com.example.wilker.demo.entity.Vendedor;
import org.springframework.data.domain.Page;

public interface VendedorService {
    Vendedor cadastrar(VendedorDTO vendedorDTO);

    Vendedor atualizar(Integer id, VendedorDTO vendedorDTO);

    Vendedor recuperarPorID(Integer id);

    Page<Vendedor> recuperarTodos(Integer page, Integer size, String cpf);

    Vendedor deletar(Integer id);
}
