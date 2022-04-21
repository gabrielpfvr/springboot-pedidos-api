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
	public ResponseEntity<List<Pedido>> listar() {
		return ResponseEntity.ok().body(pedidoService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pedido>> listarPorId(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoService.listarPorId(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Pedido> cadastrar(@RequestBody @Valid Pedido pedido, UriComponentsBuilder uriBuilder) {
		pedido = pedidoService.novoPedido(pedido);
		this.rabbitmqService.enviarMensagem("Pedidos", pedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		pedido.setId(id);
		pedidoService.atualizarPedido(pedido);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		pedidoService.removerPedido(id);
		return ResponseEntity.ok().build();
	}

}
