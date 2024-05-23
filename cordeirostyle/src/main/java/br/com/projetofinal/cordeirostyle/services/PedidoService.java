package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
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
	
	public List<PedidoDto> findAllDto() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDto> pedidoDto = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			PedidoDto pedidoTransformado = modelMapper.map(pedido, PedidoDto.class);
			
			Cliente cliente = pedido.getCliente();
			Endereco endereco = cliente.getEndereco();
			
			ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
			EnderecoDto enderecoDto = modelMapper.map(endereco, EnderecoDto.class);
			
			clienteDto.setEnderecoDto(enderecoDto);
			pedidoTransformado.setCliente(clienteDto);
			
			pedidoDto.add(pedidoTransformado);
		}
        return pedidoDto;
    }
	
	public PedidoDto findByIdDto(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		PedidoDto pedidoDto = modelMapper.map(pedido, PedidoDto.class);
		
		Cliente cliente = pedido.getCliente();
		Endereco endereco = cliente.getEndereco();
		
		ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
		EnderecoDto enderecoDto = modelMapper.map(endereco, EnderecoDto.class);
		
		clienteDto.setEnderecoDto(enderecoDto);
		pedidoDto.setCliente(clienteDto);
		
		return pedidoDto;
	}

}
