package br.com.gabrielmotta.modules.cliente.repository;

import br.com.gabrielmotta.modules.cliente.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
