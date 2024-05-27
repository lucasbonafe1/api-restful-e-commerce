package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.EnderecoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.RelatorioPedidoDto;
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
		
//		RelatorioPedidoDto relatorioPedidoDto = modelMapper.map(pedidoDto, RelatorioPedidoDto.class);
//		emailService.enviarEmail("giuseppe@power.com", "little baby", relatorioPedidoDto.toString());
		
		return pedidoDto;
	}
		

	public PedidoDto save(PedidoDto pedidoDto) {
	    Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);
	    Pedido pedidoSalvo = pedidoRepository.save(pedido);
	    PedidoDto pedidoSalvoDto = modelMapper.map(pedidoSalvo, PedidoDto.class);
	    RelatorioPedidoDto relatorioPedidoDto = modelMapper.map(pedidoSalvo, RelatorioPedidoDto.class);
	    
		
		emailService.enviarEmail("giuseppe@power.com", "little baby", relatorioPedidoDto.toString());

	    return pedidoSalvoDto;
	}

	public PedidoDto update(Integer id, PedidoDto pedido) {
		Pedido pedidoAtualizado = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id do pedido não encontrado!"));
		PedidoDto pedidoDtoAtualizado = null;
		if (pedidoAtualizado != null) {
				pedidoAtualizado.setData_entrega(pedido.getData_entrega());
				pedidoAtualizado.setData_envio(pedido.getData_envio());
				pedidoAtualizado.setStatus(pedido.getStatus());
				pedidoAtualizado.setValor_total(pedido.getValor_total());
				pedidoDtoAtualizado = modelMapper.map(pedidoAtualizado, PedidoDto.class);
				pedidoRepository.save(pedidoAtualizado);
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
