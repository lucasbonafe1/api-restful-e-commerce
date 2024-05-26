package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.RelatorioPedidoDto;
import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.entities.Pedido;
import br.com.projetofinal.cordeirostyle.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EmailService emailService;

	public List<PedidoDto> findAll() throws NoSuchElementException {
	    List<Pedido> pedidos = pedidoRepository.findAll();
	    List<PedidoDto> pedidoDtoList = new ArrayList<>();

	    if (pedidos.isEmpty()) {
	        throw new NoSuchElementException("Não há pedidos registrados!");
	    }

	    for(Pedido pedido : pedidos) { 
	        PedidoDto pedidoTransformado = modelMapper.map(pedido, PedidoDto.class);
	        List<ItemPedido> itensPedidos = pedido.getItensPedidos();
	        List<ItemPedidoDto> itensDto = new ArrayList<>();

	        for(ItemPedido item : itensPedidos) {
	        	ItemPedidoDto itemPedidoDto = modelMapper.map(item, ItemPedidoDto.class);
	        	ProdutoDtoRetorno produtoDtoRetorno = modelMapper.map(item.getProduto(), ProdutoDtoRetorno.class);
	        	itemPedidoDto.setProdutoDto(produtoDtoRetorno);
	        	
				itensDto.add(itemPedidoDto);
			}
	        
	        pedidoTransformado.setItens(itensDto);
	        pedidoDtoList.add(pedidoTransformado);
	    }
		return pedidoDtoList;
	}


	public PedidoDto findById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pedido não encontrado!"));
		PedidoDto pedidoDto = null;
		List<ItemPedido> itensPedidos = pedido.getItensPedidos();
		List<ItemPedidoDto> itensDto = new ArrayList<>();
				
		for(ItemPedido item : itensPedidos) {
        	ItemPedidoDto itemPedidoDto = modelMapper.map(item, ItemPedidoDto.class);
        	ProdutoDtoRetorno produtoDtoRetorno = modelMapper.map(item.getProduto(), ProdutoDtoRetorno.class);
        	itemPedidoDto.setProdutoDto(produtoDtoRetorno);
        	
			itensDto.add(itemPedidoDto);
		}
		
		pedidoDto = modelMapper.map(pedido, PedidoDto.class);
		pedidoDto.setItens(itensDto);
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
		Pedido pedidoAtualizado = pedidoRepository.findById(id).orElse(null);
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
