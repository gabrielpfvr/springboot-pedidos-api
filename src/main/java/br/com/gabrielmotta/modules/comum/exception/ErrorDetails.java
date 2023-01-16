package br.com.gabrielmotta.modules.comum.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;
    private String field;

    public ErrorDetails(String message) {
        this.message = message;
    }
}
