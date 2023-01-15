package br.com.gabrielmotta.modules.cliente.model;

import br.com.gabrielmotta.modules.cliente.dto.EnderecoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @NotBlank
    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @NotBlank
    @Column(name = "LOGRADOURO", nullable = false)
    private String logradouro;

    @NotNull
    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotBlank
    @Column(name = "CEP", nullable = false)
    private String cep;

    @NotBlank
    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "REFERENCIA")
    private String referencia;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "FK_CLIENTE", referencedColumnName = "ID",
        foreignKey = @ForeignKey(name = "FK_CLIENTE_ENDERECO"), nullable = false)
    private Cliente cliente;

    public static Endereco of(EnderecoRequest request) {
        var endereco = new Endereco();
        BeanUtils.copyProperties(request, endereco);
        return endereco;
    }

    public static List<Endereco> of(List<EnderecoRequest> request) {
        return !isEmpty(request)
            ? request.stream()
            .map(Endereco::of)
            .toList()
            : Collections.emptyList();
    }
}
