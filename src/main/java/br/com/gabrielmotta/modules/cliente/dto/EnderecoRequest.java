package br.com.gabrielmotta.modules.cliente.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public record EnderecoRequest(@NotBlank String estado,
                              @NotBlank String cidade,
                              @NotBlank String logradouro,
                              @NotNull Integer numero,
                              String complemento,
                              @NotBlank String cep,
                              @NotBlank String bairro,
                              String referencia) {
}
