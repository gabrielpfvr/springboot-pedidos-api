package br.com.xbrain.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne
	private Cliente cliente;
	@ManyToMany
	private List<Produto> produtos = new ArrayList<Produto>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente.toString();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@JsonFormat(pattern="dd-MM-yyyy - HH:mm")
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public double getValorTotal() {
		double soma = 0D;
		for (Produto p : produtos) {
			soma = soma + p.getPreco();
		}
		return soma;
	}

}
