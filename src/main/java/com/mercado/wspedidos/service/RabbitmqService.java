package com.mercado.wspedidos.service;

import com.mercado.wspedidos.exceptions.ErroEnviarMensagemFilaException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(String nomeFila, Object mensagem) throws ErroEnviarMensagemFilaException {
        rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }
}
