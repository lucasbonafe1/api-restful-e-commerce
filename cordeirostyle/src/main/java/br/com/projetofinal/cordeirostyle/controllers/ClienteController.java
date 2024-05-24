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
import br.com.projetofinal.cordeirostyle.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll(){
		return new ResponseEntity<>(clienteService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
			return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
		return new ResponseEntity<>(clienteService.save(clienteDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto>update(@PathVariable Integer id, @RequestBody ClienteDto novoClienteDto) {
			return new ResponseEntity<>(clienteService.update(id, novoClienteDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDto> deleteById(@PathVariable Integer id){
		return new ResponseEntity<>(clienteService.deleteById(id), HttpStatus.OK);
	}
	
}
