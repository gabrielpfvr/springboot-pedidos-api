package br.com.xbrain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xbrain.api.model.Pedido;
import br.com.xbrain.api.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> lista() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}
	
	

}
