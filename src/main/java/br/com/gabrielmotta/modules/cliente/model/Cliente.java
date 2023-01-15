package br.com.gabrielmotta.modules.cliente.model;

import br.com.gabrielmotta.modules.cliente.dto.ClienteRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
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
}
