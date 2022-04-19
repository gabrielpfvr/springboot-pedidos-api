package br.com.xbrain.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xbrain.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
