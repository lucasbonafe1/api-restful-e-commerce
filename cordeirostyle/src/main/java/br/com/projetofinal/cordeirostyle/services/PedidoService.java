package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.ClienteDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.dtos.RelatorioPedidoDto;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.entities.Pedido;
import br.com.projetofinal.cordeirostyle.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EmailService emailService;

	@Transactional
	public List<PedidoDtoRetorno> findAll() throws NoSuchElementException {
	    List<Pedido> pedidos = pedidoRepository.findAll();
	    List<PedidoDtoRetorno> pedidosDto = new ArrayList<>();
	    
	    for (Pedido pedido : pedidos) {
			PedidoDtoRetorno pedidoDto = modelMapper.map(pedido, PedidoDtoRetorno.class);
		
			List<ItemPedido> itensPedidos = pedido.getItensPedidos();
			List<ItemPedidoDtoRetorno> itensPedidosDto = new ArrayList<>(); 

				for(ItemPedido item : itensPedidos) {
		        	ProdutoDto produtoDto = modelMapper.map(item.getProduto(), ProdutoDto.class);
					
		        	ItemPedidoDtoRetorno itemPedidoDto = modelMapper.map(item, ItemPedidoDtoRetorno.class);
		        	itemPedidoDto.setProdutoDto(produtoDto);
		        	
		        	itensPedidosDto.add(itemPedidoDto);
				}

			Endereco endereco = pedido.getCliente().getEndereco();
			
			if(endereco != null) {
				EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);
				pedidoDto.getCliente().setEnderecoDtoRetorno(enderecoDtoRetorno);
			}
			pedidoDto.setItens(itensPedidosDto);
			
			pedidosDto.add(pedidoDto);
	    }
		return pedidosDto;
	}

	@Transactional
	public PedidoDtoRetorno findById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pedido não encontrado!"));
		PedidoDtoRetorno pedidoDto = new PedidoDtoRetorno();
		pedidoDto = modelMapper.map(pedido, PedidoDtoRetorno.class);
		
		List<ItemPedido> itensPedidos = pedido.getItensPedidos();
		List<ItemPedidoDtoRetorno> itensDto = new ArrayList<>();
				
		for(ItemPedido item : itensPedidos) {
			ProdutoDto produtoDto = modelMapper.map(item.getProduto(), ProdutoDto.class);
			
			ItemPedidoDtoRetorno itemPedidoDto = modelMapper.map(item, ItemPedidoDtoRetorno.class);
        	itemPedidoDto.setProdutoDto(produtoDto);
        	
			itensDto.add(itemPedidoDto);
		}
		
		Endereco endereco = pedido.getCliente().getEndereco();
		EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);
		pedidoDto.getCliente().setEnderecoDtoRetorno(enderecoDtoRetorno);
		
		pedidoDto.setItens(itensDto);
		
		return pedidoDto;
	}
		
	@Transactional
	public PedidoDtoRetorno save(PedidoDto pedidoDto) {
	    Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);
	    Pedido pedidoSalvo = pedidoRepository.save(pedido);    
	    PedidoDtoRetorno pedidoSalvoDto = modelMapper.map(pedidoSalvo, PedidoDtoRetorno.class);
	    
	    ClienteDto cliente = clienteService.findById(pedidoDto.getCliente().getId_cliente());
	    ClienteDtoRetorno clienteRetorno = modelMapper.map(cliente, ClienteDtoRetorno.class);
	    pedidoSalvoDto.setCliente(clienteRetorno);
	    
	    return pedidoSalvoDto;
	}

	@Transactional
	public PedidoDtoRetorno update(Integer id, PedidoDto pedido) {
		Pedido pedidoAtualizado = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id do pedido não encontrado!"));
		PedidoDtoRetorno pedidoDtoAtualizado = null;
		if (pedidoAtualizado != null) {
				pedidoAtualizado.setData_entrega(pedido.getData_entrega());
				pedidoAtualizado.setData_envio(pedido.getData_envio());
				pedidoAtualizado.setStatus(pedido.getStatus());
				pedidoAtualizado.setValor_total(pedido.getValor_total());
				
				ClienteDto clienteDto = clienteService.findById(pedido.getCliente().getId_cliente());
				ClienteDtoRetorno cliente = modelMapper.map(clienteDto, ClienteDtoRetorno.class);
				
				List<ItemPedido> itensPedidos = pedidoAtualizado.getItensPedidos();
				List<ItemPedidoDtoRetorno> itensDto = new ArrayList<>();
						
				for(ItemPedido item : itensPedidos) {
					ProdutoDto produtoDto = modelMapper.map(item.getProduto(), ProdutoDto.class);
					
					ItemPedidoDtoRetorno itemPedidoDto = modelMapper.map(item, ItemPedidoDtoRetorno.class);
		        	itemPedidoDto.setProdutoDto(produtoDto);
		        	
					itensDto.add(itemPedidoDto);
				}
				
				pedidoDtoAtualizado = modelMapper.map(pedidoAtualizado, PedidoDtoRetorno.class);
				pedidoDtoAtualizado.setCliente(cliente);
				pedidoDtoAtualizado.setItens(itensDto);
				
				pedidoRepository.save(pedidoAtualizado);
				
				if(pedidoDtoAtualizado.getStatus() == true) {
					RelatorioPedidoDto relatorioPedidoDto = modelMapper.map(pedidoDtoAtualizado, RelatorioPedidoDto.class);	
					emailService.enviarEmail(pedidoDtoAtualizado.getCliente().getEmail(), "-- Relatorio do seu pedido --", relatorioPedidoDto.toString());
				}
				
		}
		
		return pedidoDtoAtualizado;
	}

	public PedidoDto deleteById(Integer id) {
		Pedido pedidoDeletado = pedidoRepository.findById(id).orElse(null);
		PedidoDto pedidoDtoDeletado = null;
		if (pedidoDeletado != null) {
			pedidoDtoDeletado = modelMapper.map(pedidoDeletado, PedidoDto.class);
			pedidoRepository.deleteById(id);
		}
		return pedidoDtoDeletado;
	}
	
}
