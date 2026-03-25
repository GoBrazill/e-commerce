package com._DM.E_commerce.services;

import com._DM.E_commerce.entities.Pedido;
import com._DM.E_commerce.dtos.PedidoDTO;
import com._DM.E_commerce.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTO criarPedido(PedidoDTO dto) {
            Pedido pedido = new Pedido(dto.getMomento(), dto.getStatus());
            pedidoRepository.save(pedido);

            return new PedidoDTO(pedido.getMomento(), pedido.getStatus());
    }

    public List<Pedido> mostrarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Pedido> listaPedidos;

        listaPedidos = pedidos;

        return listaPedidos;
    }

    public PedidoDTO mostrarPedidoPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        return new PedidoDTO(pedido.getMomento(), pedido.getStatus());
    }

    public String atualizarPedido(UUID id, PedidoDTO dto) {
        Optional<Pedido> listaPedidos = pedidoRepository.findById(id);

        if (listaPedidos.isPresent()) {
            Pedido pedido = listaPedidos.get();
            pedido.setStatus(dto.getStatus());

            return pedido.toString();
        } else {
            return "Pedido não encontrado";
        }
    }

    public String deletarPedido(UUID id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return "Pedido excluído com sucesso";
        } else {
            return "Pedido não encontrado";
        }
    }

}
