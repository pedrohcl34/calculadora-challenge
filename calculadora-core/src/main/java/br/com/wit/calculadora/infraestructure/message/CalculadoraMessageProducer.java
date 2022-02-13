package br.com.wit.calculadora.infraestructure.message;

import br.com.wit.calculadora.domain.model.ResponseOperation;
import br.com.wit.calculadora.domain.operations.Operation;
import br.com.wit.calculadora.infraestructure.message.config.MessageRabbitConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CalculadoraMessageProducer {
    @Value("${calculadora.operations.queue}")
    private String calculadoraOperationsQueue;


    private final RabbitTemplate rabbitTemplate;
    private final MessageRabbitConverter messageConverter;
    private final ObjectMapper objectMapper;

    public ResponseOperation sendOperationMessage(Operation operation){
        byte[] returnMessage = (byte[]) rabbitTemplate.convertSendAndReceive(calculadoraOperationsQueue, messageConverter.createMessage(operation));
        try {
            return objectMapper.readValue(returnMessage, ResponseOperation.class);
        } catch (IOException e) {
            return new ResponseOperation("An error occurred during the calculation", true);
        }
    }

}
