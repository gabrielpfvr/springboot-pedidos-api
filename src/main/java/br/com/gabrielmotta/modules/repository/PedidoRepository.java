package br.com.gabrielmotta.modules.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielmotta.modules.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
}
