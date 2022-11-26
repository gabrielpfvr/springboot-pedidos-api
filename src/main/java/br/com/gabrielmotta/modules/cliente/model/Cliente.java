package br.com.gabrielmotta.modules.cliente.model;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 6998504629768901873L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name = "NOME")
	private String nome;

	@NotBlank
	@Column(name = "EMAIL")
	private String email;

	@NotBlank
	@Column(name = "TELEFONE")
	private String telefone;

	@Embedded
	private Endereco endereco;

	public Cliente(Integer id) {
		this.id = id;
	}

	public static Cliente of(ClienteRequest request) {
		var cliente = new Cliente();
		BeanUtils.copyProperties(request, cliente);
		return cliente;
	}
}
