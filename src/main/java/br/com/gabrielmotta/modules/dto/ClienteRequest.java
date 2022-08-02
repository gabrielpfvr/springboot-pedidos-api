package br.com.gabrielmotta.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotEmpty
    private String nome;
    @NotEmpty
    private String email;
    @NotEmpty
    private String telefone;
    @NotNull
    @Valid
    private EnderecoRequest endereco;
}
