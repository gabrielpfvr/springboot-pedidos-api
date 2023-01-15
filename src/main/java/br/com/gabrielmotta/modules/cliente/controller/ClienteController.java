package br.com.gabrielmotta.modules.cliente.controller;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import br.com.gabrielmotta.modules.cliente.dto.ClienteResponse;
import br.com.gabrielmotta.modules.cliente.dto.EnderecoRequest;
import br.com.gabrielmotta.modules.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public void save(@RequestBody @Validated ClienteRequest request) {
        service.save(request);
    }

    @GetMapping
    public List<ClienteResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ClienteResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Validated ClienteRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleve(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("{id}/adicionar-endereco")
    public void adicionarEndereco(@PathVariable Integer id, @Validated @RequestBody EnderecoRequest enderecoRequest) {
        service.adicionarEndereco(id, enderecoRequest);
    }
}
