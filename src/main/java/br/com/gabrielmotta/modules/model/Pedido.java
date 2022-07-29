package br.com.gabrielmotta.modules.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gabrielmotta.modules.dto.PedidoRequest;
import lombok.*;

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
	private List<Produto> produtos;
}
