package br.com.gabrielmotta.modules.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gabrielmotta.modules.dto.PedidoRequest;
import br.com.gabrielmotta.modules.dto.PedidoResponse;
import br.com.gabrielmotta.modules.repository.PedidoRepository;
import br.com.gabrielmotta.modules.handler.PedidoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmotta.modules.model.Pedido;

import javax.transaction.Transactional;

@Service
public class PedidoService {

	private static final PedidoException PEDIDO_NAO_ENCONTRADO =
			new PedidoException("Pedido não encontrado.");
	@Autowired
	private PedidoRepository repository;
	
	public List<PedidoResponse> findAll() {
		return repository.findAll().stream().map(PedidoResponse::of)
				.collect(Collectors.toList());
	}
	
	private Pedido findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> PEDIDO_NAO_ENCONTRADO);
	}

	public PedidoResponse detalhar(Integer id) {
		return PedidoResponse.of(findById(id));
	}
	 
	public void create(PedidoRequest request) {
		repository.save(Pedido.of(request));
	}

	@Transactional
	public void update(Integer id, PedidoRequest request) {
		var pedido = findById(id);
		BeanUtils.copyProperties(Pedido.of(request), pedido, "id");
		repository.save(pedido);
	}
	
	public void delete(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}

	private void existsById(Integer id) {
		if (!repository.existsById(id)) throw PEDIDO_NAO_ENCONTRADO;
	}
}
