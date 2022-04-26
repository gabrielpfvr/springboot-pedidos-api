package br.com.xbrain.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xbrain.api.model.Pedido;
import br.com.xbrain.api.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findOrders() {
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> findById(Long id) {
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
	
	public void deleteOrder(Long id) {
		findById(id);
		pedidoRepository.deleteById(id);
	}
	
}
