package br.com.gabrielmotta.modules.controller;

import br.com.gabrielmotta.modules.dto.PedidoRequest;
import br.com.gabrielmotta.modules.dto.PedidoResponse;
import br.com.gabrielmotta.modules.service.PedidoService;
import br.com.gabrielmotta.modules.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidoService service;
	@Autowired
	private RabbitMQService rabbitmqService;

	@GetMapping
	public ResponseEntity<List<PedidoResponse>> getAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public PedidoResponse findById(@PathVariable Integer id) {
		return service.detalhar(id);
	}
	
	@PostMapping
	public void create(@RequestBody @Validated PedidoRequest request) {
		service.create(request);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable Integer id, @RequestBody @Validated PedidoRequest request) {
		service.update(id, request);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
