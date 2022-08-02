package br.com.gabrielmotta.modules.model;

import br.com.gabrielmotta.modules.dto.PedidoRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1737139092649314290L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DATA_CRIACAO", updatable = false)
	private LocalDateTime dataCriacao;

	@NotNull
	@ManyToOne
	private Cliente cliente;

	@NotEmpty
	@ManyToMany
	@ToString.Exclude
	@JoinTable(name = "PEDIDO_PRODUTO",
		joinColumns = @JoinColumn(name = "PEDIDO_ID",
			foreignKey = @ForeignKey(name = "FK_PEDIDO")),
		inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID",
			foreignKey = @ForeignKey(name = "FK_PEDIDO_PRODUTO_ID")))
	private List<Produto> produtos;

	public static Pedido of(PedidoRequest request) {
		return Pedido.builder()
				.dataCriacao(LocalDateTime.now())
				.cliente(new Cliente(request.getClienteId()))
				.produtos(request.getProdutosIds().stream().map(Produto::new).collect(Collectors.toList()))
				.build();
	}
}
