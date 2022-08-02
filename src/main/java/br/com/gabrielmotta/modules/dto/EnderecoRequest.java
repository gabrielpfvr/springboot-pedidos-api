package br.com.gabrielmotta.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    @NotEmpty
    private String logradouro;
    @NotEmpty
    private String numero;
    @NotEmpty
    private String cep;
    @NotEmpty
    private String bairro;
    @NotEmpty
    private String cidade;
    @NotEmpty
    private String estado;
}
