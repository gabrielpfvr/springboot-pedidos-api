package br.com.gabrielmotta.modules.produto.controller;

import br.com.gabrielmotta.modules.produto.dto.ProdutoRequest;
import br.com.gabrielmotta.modules.produto.dto.ProdutoResponse;
import br.com.gabrielmotta.modules.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public void save(@RequestBody @Validated ProdutoRequest request) {
        service.save(request);
    }

    @GetMapping
    public List<ProdutoResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public ProdutoResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Validated ProdutoRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleve(@PathVariable Integer id) {
        service.delete(id);
    }
}
