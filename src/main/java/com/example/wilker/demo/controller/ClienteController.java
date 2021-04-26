package com.example.wilker.demo.controller;

import com.example.wilker.demo.dto.ClienteDTO;
import com.example.wilker.demo.entity.Cliente;
import com.example.wilker.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrar(@RequestBody ClienteDTO clienteDTO){
        return this.clienteService.cadastrar(clienteDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO){
        this.clienteService.atualizar(id, clienteDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente recuperarPorID(@PathVariable Integer id){
        return this.clienteService.recuperarPorID(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Cliente> recuperarTodos(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf){
        return this.clienteService.recuperarTodos(page, size, cpf);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        this.clienteService.deletar(id);
    }
}
