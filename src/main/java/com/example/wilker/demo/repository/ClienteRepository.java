package com.example.wilker.demo.repository;

import com.example.wilker.demo.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Page<Cliente> findAllByCpfStartsWith(String cpf, Pageable pageable);
    Cliente findByCpf(String cpf);
}
