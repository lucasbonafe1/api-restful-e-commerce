package br.com.projetofinal.cordeirostyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.Pedido;
import br.com.projetofinal.cordeirostyle.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido update(Integer id, Pedido pedido) {
		Pedido pedidoAtualizado = pedidoRepository.findById(id).orElse(null);
		if (pedidoAtualizado != null) {
			try {
				pedidoAtualizado.setData_entrega(pedido.getData_entrega());
				pedidoAtualizado.setData_envio(pedido.getData_envio());
				pedidoAtualizado.setStatus(pedido.getStatus());
				pedidoAtualizado.setValor_total(pedido.getValor_total());
				pedidoRepository.save(pedidoAtualizado);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return pedidoAtualizado;
	}

	public Pedido deleteById(Integer id) {
		Pedido pedidoDeletado = pedidoRepository.findById(id).orElse(null);
		if (pedidoDeletado != null) {
			try {
				pedidoRepository.deleteById(id);
				return pedidoDeletado;
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return pedidoDeletado;
	}

}
