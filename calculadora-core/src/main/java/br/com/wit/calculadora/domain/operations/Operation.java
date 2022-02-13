package br.com.wit.calculadora.domain.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public abstract class Operation implements Serializable {
    protected BigDecimal a;
    protected BigDecimal b;

    protected Operation(BigDecimal a, BigDecimal b){
        this.a = a;
        this.b = b;
    }

    public abstract BigDecimal calculate();
}
