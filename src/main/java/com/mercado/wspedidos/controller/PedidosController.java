package com.mercado.wspedidos.controller;

import com.mercado.ws.entity.dto.AtualizarStatusPedidoDTO;
import com.mercado.ws.entity.dto.PedidoDTO;
import com.mercado.wspedidos.exceptions.ErroEnviarMensagemFilaException;
import com.mercado.wspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "pedidos")
public class PedidosController {

    private PedidoService pedidoService;

    @Autowired
    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("novo")
    private ResponseEntity<?> novoPedido(@RequestBody PedidoDTO pedido) {
        try {
            return ResponseEntity.ok().body(pedidoService.enviarPedido(pedido));
        } catch (ErroEnviarMensagemFilaException erro) {
            return ResponseEntity.internalServerError().body(erro);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno");
        }
    }

    @PostMapping("atualizar")
    private ResponseEntity<?> atualizarStatusPedido(@RequestBody AtualizarStatusPedidoDTO atualizarStatusPedido) {
        try {
            pedidoService.atualizarStatusPedido(atualizarStatusPedido);
            return ResponseEntity.ok().body("Solicitação de atualização enviada");
        } catch (ErroEnviarMensagemFilaException erro) {
            return ResponseEntity.internalServerError().body(erro);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno");
        }
    }

    @GetMapping("{codigo}")
    private ResponseEntity<?> buscarPedido(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok().body(pedidoService.buscarPedidoByCodigo(codigo));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno");
        }

    }

}
