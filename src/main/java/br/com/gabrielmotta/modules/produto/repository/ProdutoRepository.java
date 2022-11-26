package br.com.gabrielmotta.modules.produto.repository;

import br.com.gabrielmotta.modules.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByDescricaoIgnoreCase(String descricao);
}
