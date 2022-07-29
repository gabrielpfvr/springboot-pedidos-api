package br.com.gabrielmotta.modules.service;

import java.util.List;
import java.util.Optional;

import br.com.gabrielmotta.modules.repository.PedidoRepository;
import br.com.gabrielmotta.config.handler.PedidoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmotta.modules.model.Pedido;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findOrders() {
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if(pedido.isEmpty()) {
			throw new PedidoNaoEncontradoException();
		}
		return pedido;
	}
	 
	public Pedido newOrder(Pedido pedido) {
		pedido.setId(null);
		return pedidoRepository.saveAndFlush(pedido);
	}
	
	public void updateOrder(Pedido pedido) {
		findById(pedido.getId());
		pedidoRepository.save(pedido);
	}
	
	public void deleteOrder(Integer id) {
		findById(id);
		pedidoRepository.deleteById(id);
	}
	
}
