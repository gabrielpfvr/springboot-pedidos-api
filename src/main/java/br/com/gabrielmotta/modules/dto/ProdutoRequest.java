package br.com.gabrielmotta.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    private Double preco;
}
