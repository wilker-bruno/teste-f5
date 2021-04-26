package com.example.wilker.demo.service.serviceImpl;

import com.example.wilker.demo.dto.ClienteDTO;
import com.example.wilker.demo.entity.Cliente;
import com.example.wilker.demo.repository.ClienteRepository;
import com.example.wilker.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente cadastrar(ClienteDTO clienteDTO) {
        Cliente clienteByCPF = this.clienteRepository.findByCpf(clienteDTO.getCpf());

        if(clienteByCPF != null)
            throw new RuntimeException("Já existe um Usuário cadastrado com CPF informado");

        Cliente clienteNovo = new Cliente();
        clienteNovo.setNome(clienteDTO.getNome());
        clienteNovo.setCpf(clienteDTO.getCpf());

        return this.clienteRepository.save(clienteNovo);
    }

    @Override
    public Cliente atualizar(Integer id, ClienteDTO clienteDTO) {
        Cliente clienteByID = this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Usuário para o ID informado"));

        if(clienteDTO.getCpf() != null && !clienteByID.getCpf().equals(clienteDTO.getCpf())){
            Cliente clienteByCPF = this.clienteRepository.findByCpf(clienteDTO.getCpf());

            if(clienteByCPF.getId() != id)
                throw new RuntimeException("Já existe um Usuário cadastrado com CPF informado");
        }

        Cliente clienteNovo = new Cliente();
        clienteNovo.setId(id);
        clienteNovo.setNome(clienteDTO.getNome());
        clienteNovo.setCpf(clienteDTO.getCpf());

        return this.clienteRepository.save(clienteNovo);
    }

    @Override
    public Cliente recuperarPorID(Integer id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Usuário para o ID informado"));
    }

    @Override
    public Page<Cliente> recuperarTodos(Integer page, Integer size, String cpf) {
        return this.clienteRepository.findAllByCpfStartsWith(cpf, PageRequest.of(page, size));
    }

    @Override
    public Cliente deletar(Integer id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há Usuário para o ID informado"));

        this.clienteRepository.deleteById(id);

        return cliente;
    }
}
