package br.com.wit.calculadora.tasks;

import br.com.wit.calculadora.domain.model.ResponseOperation;
import br.com.wit.calculadora.domain.operations.Operation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
@RequiredArgsConstructor
public class OperationsTaskHandler {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${calculadora.operations.queue}")
    public void calculateOperation(Message message, Channel channel){
        log.info("OperationsTaskHandler started consuming message from {}",message.getMessageProperties().getCorrelationId());
        String result = "";
        Boolean error = false;
        Operation operation = (Operation) SerializationUtils.deserialize(message.getBody());
        try {
            result = operation.calculate() + "";
        } catch (Exception e){
            error = true;
            result = e.getMessage();
        }
        BasicProperties basicProperties = new BasicProperties()
                .builder()
                .correlationId(message.getMessageProperties().getCorrelationId())
                .contentType(MediaType.APPLICATION_JSON.getType())
                .contentEncoding(StandardCharsets.UTF_8.name())
                .build();

        try {
            channel.basicPublish("",message.getMessageProperties().getReplyTo(),basicProperties, objectMapper.writeValueAsBytes(new ResponseOperation(result, error)));
        } catch (IOException e) {
            log.error("Error at br.com.wit.calculadora.tasks.OperationsTaskHandler {}",e);
        }

    }

}
