package com.mercado.ws.entity;

import com.mercado.ws.entity.dto.PedidoDTO;
import com.mercado.ws.entity.enums.StatusPedido;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String codigo;
    private Integer codUsuario;
    private Integer codMercado;
    private StatusPedido statusPedido;
    private String descricao;
    private Integer quantidade;
    private Double valor;

    public Pedido() {
    }

    public Pedido(PedidoDTO pedido) {
        this.codigo = pedido.getCodigo();
        this.codUsuario = pedido.getCodUsuario();
        this.codMercado = pedido.getCodMercado();
        this.statusPedido = pedido.getStatusPedido();
        this.descricao = pedido.getDescricao();
        this.quantidade = pedido.getQuantidade();
        this.valor = pedido.getValor();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getCodMercado() {
        return codMercado;
    }

    public void setCodMercado(Integer codMercado) {
        this.codMercado = codMercado;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
