package br.com.gabrielmotta.modules.pedido.model;

import br.com.gabrielmotta.modules.cliente.dto.EnderecoResponse;
import br.com.gabrielmotta.modules.cliente.model.Cliente;
import br.com.gabrielmotta.modules.pedido.dto.PedidoRequest;
import br.com.gabrielmotta.modules.pedido.enums.EStatusPedido;
import br.com.gabrielmotta.modules.produto.model.Produto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "DATA_CRIACAO", updatable = false)
    private LocalDateTime dataCriacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FK_CLIENTE", nullable = false, foreignKey = @ForeignKey(name = "FK_CLIENTE_PEDIDO"))
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

    @NotNull
    @Column(name = "ENDERECO_ENTREGA_ID", nullable = false)
    private Integer enderecoEntregaId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private EStatusPedido statusPedido;

    public static Pedido of(PedidoRequest request) {
        return Pedido.builder()
            .dataCriacao(LocalDateTime.now())
            .cliente(new Cliente(request.clienteId()))
            .produtos(request.produtosIds().stream().map(Produto::new).toList())
            .enderecoEntregaId(request.enderecoEntregaId())
            .statusPedido(EStatusPedido.CRIADO)
            .build();
    }

    public EnderecoResponse getEnderecoEntrega() {
        return this.getCliente().getEnderecosEntrega()
            .stream()
            .filter(endereco -> Objects.equals(this.getEnderecoEntregaId(), endereco.getId()))
            .map(EnderecoResponse::of)
            .findFirst()
            .orElse(null);
    }
}
