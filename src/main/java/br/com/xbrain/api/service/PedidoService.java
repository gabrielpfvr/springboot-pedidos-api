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
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> listarPorId(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if(pedido.isEmpty()) {
			throw new PedidoNaoEncontradoException();
		}
		return pedido;
	}
	 
	public Pedido novoPedido(Pedido pedido) {
		pedido.setId(null);
		return pedidoRepository.saveAndFlush(pedido);
	}
	
	public void atualizarPedido(Pedido pedido) {
		listarPorId(pedido.getId());
		pedidoRepository.save(pedido);
	}
	
	public void removerPedido(Long id) {
		listarPorId(id);
		pedidoRepository.deleteById(id);
	}
	
}
