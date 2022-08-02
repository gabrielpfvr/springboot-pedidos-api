package br.com.gabrielmotta.modules.dto;

import br.com.gabrielmotta.modules.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;

    public static EnderecoResponse of(Endereco endereco) {
        var response = new EnderecoResponse();
        BeanUtils.copyProperties(endereco, response);
        return response;
    }
}
