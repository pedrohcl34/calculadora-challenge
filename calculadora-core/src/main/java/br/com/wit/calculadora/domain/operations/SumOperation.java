package br.com.wit.calculadora.domain.operations;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SumOperation extends Operation {

    public SumOperation(BigDecimal a, BigDecimal b){
        super(a,b);
    }
    @Override
    public BigDecimal calculate() {
        return a.add(b);
    }
}
