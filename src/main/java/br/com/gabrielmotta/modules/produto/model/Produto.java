package br.com.gabrielmotta.modules.produto.model;

import br.com.gabrielmotta.modules.produto.dto.ProdutoRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUTO")
public class Produto {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name = "NOME")
	private String descricao;

	@NotNull
	@Min(value = 0)
	@Column(name = "PRECO")
	private Double preco;

	public Produto(Integer id) {
		this.id = id;
	}

	public static Produto of(ProdutoRequest request) {
		var produto = new Produto();
		BeanUtils.copyProperties(request, produto);
		return produto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) { return false; }
		var produto = (Produto) obj;
		return id != null && Objects.equals(id, produto.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
