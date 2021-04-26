package com.example.wilker.demo.service.serviceImpl;

import com.example.wilker.demo.dto.VendedorDTO;
import com.example.wilker.demo.entity.Cliente;
import com.example.wilker.demo.entity.Vendedor;
import com.example.wilker.demo.repository.ClienteRepository;
import com.example.wilker.demo.repository.VendedorRepository;
import com.example.wilker.demo.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Vendedor cadastrar(VendedorDTO vendedorDTO) {
        Vendedor vendedorByCPF = this.vendedorRepository.findByCpf(vendedorDTO.getCpf());

        if(vendedorByCPF != null)
            throw new RuntimeException("Já existe um Vendedor cadastrado com CPF informado");

        List<Cliente> clientes = new ArrayList<>();

        Vendedor vendedorNovo = new Vendedor();
        for(Integer id : vendedorDTO.getClientes()){
            Optional<Cliente> cliente = this.clienteRepository.findById(id);

            if(!cliente.isPresent())
                throw new RuntimeException("Não há Cliente para o ID informado");

            Cliente c = cliente.get();
            c.getVendedores().add(vendedorNovo);

            clientes.add(c);
        }

        vendedorNovo.setNome(vendedorDTO.getNome());
        vendedorNovo.setCpf(vendedorDTO.getCpf());
        vendedorNovo.setClientes(clientes);

        return this.vendedorRepository.save(vendedorNovo);
    }

    @Override
    public Vendedor atualizar(Integer id, VendedorDTO vendedorDTO) {
        Vendedor vendedorByID = this.vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Vendedor para o ID informado"));

        if(vendedorDTO.getCpf() != null && !vendedorByID.getCpf().equals(vendedorDTO.getCpf())){
            Vendedor vendedorByCPF = this.vendedorRepository.findByCpf(vendedorDTO.getCpf());

            if(vendedorByCPF.getId() != id)
                throw new RuntimeException("Já existe um Vendedor cadastrado com CPF informado");
        }

        List<Cliente> clientes = new ArrayList<>();

        for(Cliente cliente: vendedorByID.getClientes()){
            cliente.getVendedores().remove(vendedorByID);
        }

        for(Integer clienteID : vendedorDTO.getClientes()){
            Optional<Cliente> cliente = this.clienteRepository.findById(clienteID);

            if(!cliente.isPresent())
                throw new RuntimeException("Não há Cliente para o ID informado");

            Cliente c = cliente.get();
            c.getVendedores().add(vendedorByID);

            clientes.add(c);
        }

        vendedorByID.setNome(vendedorDTO.getNome());
        vendedorByID.setCpf(vendedorDTO.getCpf());
        vendedorByID.setClientes(clientes);

        return this.vendedorRepository.save(vendedorByID);
    }

    @Override
    public Vendedor recuperarPorID(Integer id) {
        return this.vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Vendedor para o ID informado"));
    }

    @Override
    public Page<Vendedor> recuperarTodos(Integer page, Integer size, String cpf) {
        return this.vendedorRepository.findAllByCpfStartsWith(cpf, PageRequest.of(page, size));
    }

    @Override
    public Vendedor deletar(Integer id) {
        Vendedor vendedor = this.vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Vendedor para o ID informado"));

        for(Cliente cliente: vendedor.getClientes()){
            cliente.getVendedores().remove(vendedor);
        }

        this.vendedorRepository.deleteById(id);

        return vendedor;
    }
}
