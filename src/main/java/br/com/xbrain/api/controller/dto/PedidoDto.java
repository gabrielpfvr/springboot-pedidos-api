package br.com.xbrain.api.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.xbrain.api.model.Cliente;
import br.com.xbrain.api.model.Pedido;
import br.com.xbrain.api.model.Produto;

public class PedidoDto {
	
	private Long id;
	private Cliente cliente;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private List<Produto> produtos = new ArrayList<Produto>();
	

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		//this.cliente = pedido.getCliente();
		this.dataCriacao = pedido.getDataCriacao();
		this.produtos = pedido.getProdutos();
	}


	public Long getId() {
		return id;
	}


	public Long getCliente() {
		return cliente.getId();
	}


	public List<Produto> getProdutos() {
		return produtos;
	}
	
}
