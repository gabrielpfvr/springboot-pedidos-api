package br.com.gabrielmotta.modules.model;

import br.com.gabrielmotta.modules.dto.ProdutoRequest;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 6195052501513306394L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Min(value = 0)
	@Column(name = "PRECO")
	private Double preco;

	public Produto(Integer id) {
		this.id = id;
	}

	public Produto of(ProdutoRequest request) {
		var produto = new Produto();
		BeanUtils.copyProperties(request, produto);
		return produto;
	}
}
