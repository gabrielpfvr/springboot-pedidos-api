package br.com.gabrielmotta.modules.cliente.model;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Column(name = "CPF")
    private String cpf;

    @NotBlank
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "TELEFONE")
    private String telefone;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Endereco> enderecosEntrega;

    public Cliente(Integer id) {
        this.id = id;
    }

    public static Cliente of(ClienteRequest request) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        cliente.setEnderecosEntrega(Endereco.of(request.enderecosEntregaRequest()));
        return cliente;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) { return false; }
        var cliente = (Cliente) obj;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
