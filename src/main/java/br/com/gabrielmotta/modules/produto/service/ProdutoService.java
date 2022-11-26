package br.com.gabrielmotta.modules.produto.service;

import br.com.gabrielmotta.modules.comum.exception.NotFoundException;
import br.com.gabrielmotta.modules.comum.exception.ValidationException;
import br.com.gabrielmotta.modules.produto.dto.ProdutoRequest;
import br.com.gabrielmotta.modules.produto.dto.ProdutoResponse;
import br.com.gabrielmotta.modules.produto.model.Produto;
import br.com.gabrielmotta.modules.produto.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    private static final ValidationException PRODUTO_JA_EXISTENTE =
            new ValidationException("Produto já existente com esse nome!");
    private static final NotFoundException PRODUTO_NAO_ENCONTRADO = new NotFoundException("Produto não encontrado!");

    @Autowired
    private ProdutoRepository repository;

    public void save(ProdutoRequest request) {
        validarProdutoExistente(request);
        repository.save(Produto.of(request));
    }

    public List<ProdutoResponse> getAll() {
        return ProdutoResponse.of(repository.findAll());
    }

    public ProdutoResponse getById(Integer id) {
        return ProdutoResponse.of(findById(id));
    }

    @Transactional
    public void update(Integer id, ProdutoRequest request) {
        var produto = findById(id);
        BeanUtils.copyProperties(request, produto);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw PRODUTO_NAO_ENCONTRADO;
        }
    }

    private Produto findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> PRODUTO_NAO_ENCONTRADO);
    }

    private void validarProdutoExistente(ProdutoRequest request) {
        if (repository.existsByDescricaoIgnoreCase(request.descricao())) {
            throw PRODUTO_JA_EXISTENTE;
        }
    }
}
