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
import br.com.projetofinal.cordeirostyle.entities.Pedido;
import br.com.projetofinal.cordeirostyle.services.PedidoService;


@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		return new ResponseEntity<>(pedidoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id){
		Pedido pedidoEncontrado = pedidoService.findById(id);
		if (pedidoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pedidoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido>update(@PathVariable Integer id,@RequestBody Pedido pedidoNovo){
		Pedido pedidoUpdate = pedidoService.update(id,pedidoNovo);
		if(pedidoUpdate != null) {
			 return new ResponseEntity<>(pedidoUpdate, HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pedido> deleteById(@PathVariable Integer id) {
		Pedido pedidoDeletado = pedidoService.findById(id);

		if (pedidoDeletado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pedidoService.deleteById(id);
		return new ResponseEntity<>(pedidoDeletado, HttpStatus.OK);
	}
	

	@GetMapping("/dto")
	public ResponseEntity<List<PedidoDto>> findAllPedidoResumido() {
	return new ResponseEntity<>(pedidoService.findAllDto(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDto> findByIdDto(@PathVariable Integer id) {
		PedidoDto pedidoEncontrado = pedidoService.findByIdDto(id);
		if (pedidoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoEncontrado,HttpStatus.OK);
		}
	}
	
}
