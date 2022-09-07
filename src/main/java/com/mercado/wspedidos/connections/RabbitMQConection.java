package com.mercado.wspedidos.connections;

import com.mercado.wspedidos.constants.FilaConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adicionaFilas() {
        Queue filaPedido = fila(FilaConstants.FILA_PEDIDOS);
        DirectExchange troca = trocaDireta();
        Binding ligacaoPedido = relacionamento(filaPedido, troca);
        amqpAdmin.declareQueue(filaPedido);
        amqpAdmin.declareExchange(troca);
        amqpAdmin.declareBinding(ligacaoPedido);
    }
}