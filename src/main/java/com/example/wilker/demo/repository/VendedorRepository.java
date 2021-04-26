package com.example.wilker.demo.repository;

import com.example.wilker.demo.entity.Cliente;
import com.example.wilker.demo.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Page<Vendedor> findAllByCpfStartsWith(String cpf, Pageable pageable);
    Vendedor findByCpf(String cpf);
}
