package br.com.gabrielmotta.modules.cliente.repository;

import br.com.gabrielmotta.modules.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCpf(String cpf);
}
