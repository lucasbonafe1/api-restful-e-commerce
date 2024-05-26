package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoService produtoService;

	@Autowired
	ModelMapper modelMapper;
	public List<ItemPedidoDto> findAll() {
	    List<ItemPedido> items = itemPedidoRepository.findAll();
	    List<ItemPedidoDto> itemsDto = new ArrayList<>();

	    if (items.isEmpty()) {
	        throw new NoSuchElementException("Não há items pedidos registrados!");
	    }
	    for (ItemPedido item : items) {
	        ItemPedidoDto itemTransformado = modelMapper.map(item, ItemPedidoDto.class);

	        if (item.getProduto() != null) {
	            ProdutoDtoRetorno produtoDtoRetorno = modelMapper.map(item.getProduto(), ProdutoDtoRetorno.class);
	            itemTransformado.setProdutoDto(produtoDtoRetorno);
	        } else {
	            System.out.println("Esse produto é nulo: " + item.getProduto()); 
	        }
	        
	        itemsDto.add(itemTransformado);
	    }
	    return itemsDto;
	}


	public ItemPedidoDto findById(Integer id) {
		ItemPedido item = itemPedidoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Item não encontrado!"));
		ItemPedidoDto itemPedidoDto = null;
		if (item != null) {
			itemPedidoDto = modelMapper.map(item, ItemPedidoDto.class);
			ProdutoDtoRetorno produtoDtoRetorno = modelMapper.map(item.getProduto(), ProdutoDtoRetorno.class);
			itemPedidoDto.setProdutoDto(produtoDtoRetorno);
		}
		return itemPedidoDto;
	}

	public ItemPedidoDtoRetorno save(ItemPedidoDto itemPedidoDto) {
		ItemPedido item = modelMapper.map(itemPedidoDto, ItemPedido.class);
		ItemPedido itemSalvo = itemPedidoRepository.save(item);
		ItemPedidoDtoRetorno itemRetorno = modelMapper.map(itemSalvo, ItemPedidoDtoRetorno.class);
		
		ProdutoDto produtoDtoRetorno = produtoService.findById(itemPedidoDto.getProduto().getId_produto());
		itemRetorno.setProdutoDto(produtoDtoRetorno);
		
		return itemRetorno;
	}

	public ItemPedidoDtoRetorno update(Integer id, ItemPedidoDto novoItemPedidoDto) {
		ItemPedido itemPedidoAtualizado = itemPedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Item não encontrado!"));
		ItemPedidoDtoRetorno itemDtoAtualizado = null;
		if (itemPedidoAtualizado != null) {
			
				itemPedidoAtualizado.setPercentual_desconto(novoItemPedidoDto.getPercentual_desconto());
				itemPedidoAtualizado.setPreco_venda(novoItemPedidoDto.getPreco_venda());
				itemPedidoAtualizado.setQuantidade(novoItemPedidoDto.getQuantidade());
				itemDtoAtualizado = modelMapper.map(itemPedidoAtualizado, ItemPedidoDtoRetorno.class);
				
				ProdutoDto produtoDtoRetorno = produtoService.findById(novoItemPedidoDto.getProduto().getId_produto());
				itemDtoAtualizado.setProdutoDto(produtoDtoRetorno);
				
				itemPedidoRepository.save(itemPedidoAtualizado);
		}
		return itemDtoAtualizado;
	}

	public ItemPedidoDtoRetorno deleteById(Integer id) {
		ItemPedido itemPedidoDeletado = itemPedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Item não encontrado!"));
		ItemPedidoDtoRetorno itemPedidoDeletadoDto = null;
		if (itemPedidoDeletado != null) {
			itemPedidoDeletadoDto = modelMapper.map(itemPedidoDeletado, ItemPedidoDtoRetorno.class);
			itemPedidoRepository.deleteById(id);
		}
		return itemPedidoDeletadoDto;
	}

}
