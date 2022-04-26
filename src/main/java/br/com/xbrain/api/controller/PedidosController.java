package br.com.xbrain.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.xbrain.api.model.Pedido;
import br.com.xbrain.api.service.PedidoService;
import br.com.xbrain.api.service.RabbitMQService;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
	

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private RabbitMQService rabbitmqService;
	

	@GetMapping
	public ResponseEntity<List<Pedido>> read() {
		return ResponseEntity.ok().body(pedidoService.findOrders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pedido>> readById(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoService.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido pedido, UriComponentsBuilder uriBuilder) {
		pedido = pedidoService.newOrder(pedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		
		Optional<Pedido> pedidoOptional = pedidoService.findById(pedido.getId());
		this.rabbitmqService.sendMessage("Pedidos", pedidoOptional.get());
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		pedido.setId(id);
		pedidoService.updateOrder(pedido);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pedidoService.deleteOrder(id);
		return ResponseEntity.ok().build();
	}

}
