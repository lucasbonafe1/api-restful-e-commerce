package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.entities.Pedido;
import br.com.projetofinal.cordeirostyle.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ModelMapper modelMapper;

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
	
	public List<PedidoDto> findAllPedidoResumido() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDto> pedidoDto = new ArrayList<>();
		for (Pedido Pedido : pedidos) {
			PedidoDto pedidoTransformado = modelMapper.map(Pedido, PedidoDto.class);
			pedidoDto.add(pedidoTransformado);
		}
        return pedidoDto;
    }
	
	public PedidoDto findByIdDto(Integer id) {
		PedidoDto pedidoDto = modelMapper.map(pedidoRepository.findById(id).orElse(null), PedidoDto.class);
		return pedidoDto;
	}

}
