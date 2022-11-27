package br.com.gabrielmotta.modules.pedido.enums;

import lombok.Getter;

@Getter
public enum EStatusPedido {

    CRIADO("Criado"),
    APROVADO("Aprovado"),
    ENVIADO("Enviado"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluido");

    private final String descricao;

    EStatusPedido(String descricao) {
        this.descricao = descricao;
    }
}
