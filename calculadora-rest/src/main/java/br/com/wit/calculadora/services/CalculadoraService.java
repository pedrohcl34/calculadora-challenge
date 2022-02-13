package br.com.wit.calculadora.services;

import br.com.wit.calculadora.domain.model.ResponseOperation;
import br.com.wit.calculadora.domain.operations.DivideOperation;
import br.com.wit.calculadora.domain.operations.MultiplyOperation;
import br.com.wit.calculadora.domain.operations.SubtractOperation;
import br.com.wit.calculadora.domain.operations.SumOperation;
import br.com.wit.calculadora.infraestructure.message.CalculadoraMessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculadoraService {
    private final CalculadoraMessageProducer calculadoraMessageProducer;
    private final ObjectMapper objectMapper;

    public ResponseEntity<ResponseOperation> calculateSum(BigDecimal a, BigDecimal b) throws JsonProcessingException {
        return createResponse(calculadoraMessageProducer.sendOperationMessage(new SumOperation(a,b)));
    }

    public ResponseEntity<ResponseOperation> calculateSubtraction(BigDecimal a, BigDecimal b) throws JsonProcessingException {
        return createResponse(calculadoraMessageProducer.sendOperationMessage(new SubtractOperation(a,b)));
    }

    public ResponseEntity<ResponseOperation> calculateDivision(BigDecimal a, BigDecimal b) throws JsonProcessingException {
        return createResponse(calculadoraMessageProducer.sendOperationMessage(new DivideOperation(a,b)));
    }

    public ResponseEntity<ResponseOperation> calculateMultiplication(BigDecimal a, BigDecimal b) throws JsonProcessingException {
        return createResponse(calculadoraMessageProducer.sendOperationMessage(new MultiplyOperation(a,b)));
    }


    @SuppressWarnings("rawtypes")
    private ResponseEntity createResponse(ResponseOperation responseOperation) throws JsonProcessingException {
        return (responseOperation.getError()?ResponseEntity.badRequest().body(objectMapper.writeValueAsString(responseOperation.buildResponse())): ResponseEntity.ok(objectMapper.writeValueAsString(responseOperation.buildResponse())));
    }
}
