package br.com.wit.calculadora.domain.operations;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class DivideOperation extends Operation{

    public DivideOperation(BigDecimal a, BigDecimal b){
        super(a,b);
    }

    @Override
    public BigDecimal calculate() {
        return a.divide(b);
    }
}
