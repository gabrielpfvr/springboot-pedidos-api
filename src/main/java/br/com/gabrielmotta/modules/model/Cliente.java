package br.com.gabrielmotta.modules.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 6998504629768901873L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
}
