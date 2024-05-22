package br.com.projetofinal.cordeirostyle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.services.ItemPedidoService;

@RestController
@RequestMapping("/item_pedido")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAll() {
		return new ResponseEntity<>(itemPedidoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findById(@PathVariable Integer id) {
		ItemPedido itemEncontrado = itemPedidoService.findById(id);
		if (itemEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(itemEncontrado,HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<ItemPedido> save(@RequestBody ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.save(itemPedido), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> update(@PathVariable Integer id, @RequestBody ItemPedido itemNovo) {
		ItemPedido itemAtualizado = itemPedidoService.update(id, itemNovo);
		if (itemAtualizado != null) {
			return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ItemPedido> delete(@PathVariable Integer id) {
		ItemPedido itemDeletado = itemPedidoService.findById(id);
		if (itemPedidoService == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		itemPedidoService.deleteById(id);
		return new ResponseEntity<>(itemDeletado, HttpStatus.OK);
	}

}
