package br.com.gabrielmotta.modules.pedido.repository;

import br.com.gabrielmotta.modules.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
