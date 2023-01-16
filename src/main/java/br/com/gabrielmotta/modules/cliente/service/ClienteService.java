package br.com.gabrielmotta.modules.cliente.service;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import br.com.gabrielmotta.modules.cliente.dto.ClienteResponse;
import br.com.gabrielmotta.modules.cliente.dto.EnderecoRequest;
import br.com.gabrielmotta.modules.cliente.model.Cliente;
import br.com.gabrielmotta.modules.cliente.model.Endereco;
import br.com.gabrielmotta.modules.cliente.repository.ClienteRepository;
import br.com.gabrielmotta.modules.cliente.repository.EnderecoRepository;
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

    private static final NotFoundException CLIENTE_NAO_ENCONTRADO = new NotFoundException("Cliente não encontrado!");

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public void save(ClienteRequest request) {
        validarCpfExistente(request);
        clienteRepository.save(Cliente.of(request));
    }

    public List<ClienteResponse> getAll() {
        return ClienteResponse.of(clienteRepository.findAll());
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
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw CLIENTE_NAO_ENCONTRADO;
        }
    }

    public void adicionarEndereco(Integer id, EnderecoRequest enderecoRequest) {
        var endereco = Endereco.of(enderecoRequest);
        endereco.setCliente(findById(id));
        enderecoRepository.save(endereco);
    }

    private Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> CLIENTE_NAO_ENCONTRADO);
    }

    private void validarCpfExistente(ClienteRequest request) {
        if (clienteRepository.existsByCpf(request.cpf())) {
            throw new ValidationException("Já existe um cliente cadastrado com esse CPF!");
        }
    }
}
