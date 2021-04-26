package com.example.wilker.demo.service;

import com.example.wilker.demo.dto.ClienteDTO;
import com.example.wilker.demo.entity.Cliente;
import org.springframework.data.domain.Page;

public interface ClienteService {
    Cliente cadastrar(ClienteDTO clienteDTO);

    Cliente atualizar(Integer id, ClienteDTO clienteDTO);

    Cliente recuperarPorID(Integer id);

    Page<Cliente> recuperarTodos(Integer page, Integer size, String cpf);

    Cliente deletar(Integer id);
}
