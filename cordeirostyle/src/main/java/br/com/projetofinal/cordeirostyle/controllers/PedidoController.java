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

import br.com.projetofinal.cordeirostyle.dtos.PedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.PedidoDtoRetorno;
import br.com.projetofinal.cordeirostyle.services.PedidoService;


@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<?>> findAll() {
		return new ResponseEntity<>(pedidoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		PedidoDtoRetorno pedidoEncontrado = pedidoService.findById(id);
		if (pedidoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pedidoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDtoRetorno> save(@RequestBody PedidoDto pedido) {
		return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDtoRetorno>update(@PathVariable Integer id,@RequestBody PedidoDto pedidoNovo){
		PedidoDtoRetorno pedidoUpdate = pedidoService.update(id,pedidoNovo);
		if(pedidoUpdate != null) {
			 return new ResponseEntity<>(pedidoUpdate, HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		PedidoDtoRetorno pedidoDeletado = pedidoService.findById(id);

		if (pedidoDeletado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pedidoService.deleteById(id);
		return new ResponseEntity<>(pedidoDeletado, HttpStatus.OK);
	}
	
}