package br.com.wit.calculadora.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOperation implements Serializable {

    private String result;
    private Boolean error;

    public ResponseOperation buildResponse(){
        error = null;
        return this;
    }
}
