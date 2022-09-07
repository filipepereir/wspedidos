package com.mercado.wspedidos.service;

import com.mercado.ws.entity.Pedido;
import com.mercado.ws.entity.dto.AtualizarStatusPedidoDTO;
import com.mercado.ws.entity.dto.PedidoDTO;
import com.mercado.ws.entity.enums.StatusPedido;
import com.mercado.wspedidos.constants.FilaConstants;
import com.mercado.wspedidos.exceptions.ErroEnviarMensagemFilaException;
import com.mercado.wspedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private RabbitmqService rabbitmqService;
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(RabbitmqService rabbitmqService,
                         PedidoRepository pedidoRepository) {
        this.rabbitmqService = rabbitmqService;
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTO enviarPedido(PedidoDTO pedido) throws ErroEnviarMensagemFilaException {
        var fila = FilaConstants.FILA_PEDIDOS;
        try {
            Pedido pedidoRecebido = new Pedido(pedido);
            pedidoRecebido.setStatusPedido(StatusPedido.RECEBIDO);
            var pedidoAtualizado = atualizarPedido(pedidoRecebido);
            rabbitmqService.enviarMensagem(fila, pedidoAtualizado);
            return new PedidoDTO(pedidoAtualizado);
        } catch (ErroEnviarMensagemFilaException e) {
            throw new ErroEnviarMensagemFilaException("Erro ao enviar mensagem fila: " + fila);
        }
    }

    public void atualizarStatusPedido(AtualizarStatusPedidoDTO atualizarStatusPedido) throws ErroEnviarMensagemFilaException {
        var fila = FilaConstants.FILA_ATUALIZAR_PEDIDO;
        try {
            rabbitmqService.enviarMensagem(FilaConstants.FILA_ATUALIZAR_PEDIDO, atualizarStatusPedido);
        } catch (ErroEnviarMensagemFilaException e) {
            throw new ErroEnviarMensagemFilaException("Erro ao enviar mensagem fila: " + fila);
        }
    }

    private Pedido atualizarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public PedidoDTO buscarPedidoByCodigo(String codigo) {
        return new PedidoDTO(pedidoRepository.findById(codigo).get());
    }
}
