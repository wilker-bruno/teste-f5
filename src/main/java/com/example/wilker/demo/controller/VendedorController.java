package com.example.wilker.demo.controller;

import com.example.wilker.demo.dto.VendedorDTO;
import com.example.wilker.demo.entity.Vendedor;
import com.example.wilker.demo.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor cadastrar(@RequestBody VendedorDTO vendedorDTO){
        return this.vendedorService.cadastrar(vendedorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody VendedorDTO vendedorDTO){
        this.vendedorService.atualizar(id, vendedorDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendedor recuperarporID(@PathVariable Integer id){
        return this.vendedorService.recuperarPorID(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Vendedor> recuperarTodos(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf){
        return this.vendedorService.recuperarTodos(page, size, cpf);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        this.vendedorService.deletar(id);
    }
}
