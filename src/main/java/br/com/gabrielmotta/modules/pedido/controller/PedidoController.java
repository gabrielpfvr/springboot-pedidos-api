package br.com.gabrielmotta.modules.pedido.controller;

import br.com.gabrielmotta.modules.pedido.dto.PedidoRequest;
import br.com.gabrielmotta.modules.pedido.dto.PedidoResponse;
import br.com.gabrielmotta.modules.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public PedidoResponse findById(@PathVariable Integer id) {
        return service.detalhar(id);
    }

    @PostMapping
    public void create(@RequestBody @Validated PedidoRequest request) {
        service.create(request);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Validated PedidoRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
