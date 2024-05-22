package br.com.projetofinal.cordeirostyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}

	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	public ItemPedido update(Integer id, ItemPedido novoItemPedido) {
		ItemPedido itemPedidoAtualizado = itemPedidoRepository.findById(id).orElse(null);
		if(itemPedidoAtualizado != null) {
			try {
				itemPedidoAtualizado.setPercentual_desconto(novoItemPedido.getPercentual_desconto());
				itemPedidoAtualizado.setPreco_venda(novoItemPedido.getPreco_venda());
				itemPedidoAtualizado.setQuantidade(novoItemPedido.getQuantidade());	
				itemPedidoRepository.save(itemPedidoAtualizado);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return itemPedidoAtualizado;
	}

	public ItemPedido deleteById(Integer id) {
		ItemPedido itemPedidoDeletada = itemPedidoRepository.findById(id).orElse(null);
		if (itemPedidoDeletada != null) {
			try {
				itemPedidoRepository.deleteById(id);
				return itemPedidoDeletada;
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return itemPedidoDeletada;
	}
	
	
}
