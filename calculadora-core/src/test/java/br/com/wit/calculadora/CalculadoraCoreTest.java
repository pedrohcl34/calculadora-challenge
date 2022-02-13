package br.com.wit.calculadora;

import br.com.wit.calculadora.domain.operations.DivideOperation;
import br.com.wit.calculadora.domain.operations.MultiplyOperation;
import br.com.wit.calculadora.domain.operations.SubtractOperation;
import br.com.wit.calculadora.domain.operations.SumOperation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

public class CalculadoraCoreTest {

    @Test
    public void assertSum(){
        SumOperation sumOperation = new SumOperation(BigDecimal.TEN, BigDecimal.ONE);
        Assert.assertEquals(BigDecimal.valueOf(11),sumOperation.calculate());
    }

    @Test
    public void assertSubtraction(){
        SubtractOperation subtractOperation = new SubtractOperation(BigDecimal.TEN, BigDecimal.ONE);
        Assert.assertEquals(BigDecimal.valueOf(9),subtractOperation.calculate());
    }

    @Test
    public void assertDivision(){
        DivideOperation divideOperation = new DivideOperation(BigDecimal.TEN, BigDecimal.valueOf(4));
        Assert.assertEquals(BigDecimal.valueOf(2.5),divideOperation.calculate());
    }

    @Test
    public void assertMultiplication(){
        MultiplyOperation multiplyOperation = new MultiplyOperation(BigDecimal.TEN, BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(50),multiplyOperation.calculate());
    }
}
