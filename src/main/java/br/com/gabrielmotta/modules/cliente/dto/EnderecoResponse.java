package br.com.gabrielmotta.modules.cliente.dto;

import br.com.gabrielmotta.modules.cliente.model.Endereco;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Builder
public record EnderecoResponse(Integer id, String estado, String cidade, String logradouro, Integer numero,
                               String complemento,
                               String cep, String bairro, String referencia) {

    public static EnderecoResponse of(Endereco endereco) {
        return new EnderecoResponse(
            endereco.getId(),
            endereco.getEstado(),
            endereco.getCidade(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep(),
            endereco.getBairro(),
            endereco.getReferencia()
        );
    }

    public static List<EnderecoResponse> of(List<Endereco> endereco) {
        return !isEmpty(endereco)
            ? endereco.stream()
            .map(EnderecoResponse::of)
            .toList()
            : Collections.emptyList();
    }
}
