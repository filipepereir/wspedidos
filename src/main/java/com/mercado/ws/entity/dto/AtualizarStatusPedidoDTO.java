package com.mercado.ws.entity.dto;

import com.mercado.ws.entity.enums.StatusPedido;

import java.io.Serializable;

public class AtualizarStatusPedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoPedido;
    private StatusPedido status;

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
