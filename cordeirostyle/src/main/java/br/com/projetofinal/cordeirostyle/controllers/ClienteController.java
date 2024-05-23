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

import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		return new ResponseEntity<>(clienteService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		Cliente cliente = clienteService.findById(id);
		
		if(cliente == null) {
			return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.save(cliente),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente>update(@PathVariable Integer id, @RequestBody Cliente novoCliente) {
		Cliente clienteUpdate = clienteService.findById(id);
		if(clienteUpdate != null) {
			return new ResponseEntity<>(clienteService.update(id, novoCliente), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(clienteUpdate, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteById(@PathVariable Integer id){
		Cliente cliente = clienteService.findById(id);
		if(cliente == null) {
			return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(clienteService.deleteById(id), HttpStatus.OK);
		}
	}

	@GetMapping("/dto")
	public ResponseEntity<List<ClienteDto>> findAllDto() {
		return new ResponseEntity<>(clienteService.findAllDto(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<ClienteDto> findByIdDto(@PathVariable Integer id) {
		ClienteDto clienteDto = clienteService.findByIdDto(id);
		if (clienteDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(clienteDto,HttpStatus.OK);
		}
	}
	
}
