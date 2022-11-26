package br.com.gabrielmotta.modules.cliente.service;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import br.com.gabrielmotta.modules.cliente.dto.ClienteResponse;
import br.com.gabrielmotta.modules.cliente.dto.EnderecoRequest;
import br.com.gabrielmotta.modules.cliente.model.Cliente;
import br.com.gabrielmotta.modules.cliente.model.Endereco;
import br.com.gabrielmotta.modules.cliente.repository.ClienteRepository;
import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.comum.exception.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    private static final ValidationException CPF_JA_CADASTRADO =
            new ValidationException("Já existe um cliente cadastrado com esse CPF!");
    private static final NotFoundException CLIENTE_NAO_ENCONTRADO = new NotFoundException("Cliente não encontrado!");

    @Autowired
    private ClienteRepository repository;

    public void save(ClienteRequest request) {
        validarCpfExistente(request);
        repository.save(Cliente.of(request));
    }

    public List<ClienteResponse> getAll() {
        return ClienteResponse.of(repository.findAll());
    }

    public ClienteResponse getById(Integer id) {
        return ClienteResponse.of(findById(id));
    }

    @Transactional
    public void update(Integer id, ClienteRequest request) {
        var produto = findById(id);
        BeanUtils.copyProperties(request, produto);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw CLIENTE_NAO_ENCONTRADO;
        }
    }

    @Transactional
    public void adicionarEndereco(Integer id, EnderecoRequest enderecoRequest) {
        var cliente = findById(id);
        var enderecos = cliente.getEnderecosEntrega();
        var enderecosNovos = Endereco.of(enderecoRequest);
        if (!enderecos.isEmpty()) {
            enderecos.add(enderecosNovos);
            enderecosNovos.setCliente(cliente);
        }
    }

    private Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> CLIENTE_NAO_ENCONTRADO);
    }

    private void validarCpfExistente(ClienteRequest request) {
        if (repository.existsByCpf(request.cpf())) {
            throw CPF_JA_CADASTRADO;
        }
    }
}
