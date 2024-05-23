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

import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		return new ResponseEntity<>(enderecoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id){
		Endereco enderecoEncontrado = enderecoService.findById(id);
		if (enderecoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(enderecoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.save(endereco), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco>update(@PathVariable Integer id,@RequestBody Endereco enderecoNovo){
		Endereco enderecoUpdate = enderecoService.update(id,enderecoNovo);
		if(enderecoUpdate != null) {
			 return new ResponseEntity<>(enderecoUpdate, HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Endereco> deleteById(@PathVariable Integer id) {
		Endereco enderecoDeletado = enderecoService.findById(id);

		if (enderecoDeletado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		enderecoService.deleteById(id);
		return new ResponseEntity<>(enderecoDeletado, HttpStatus.OK);
	}
	
	@GetMapping("/dto")
	public ResponseEntity<List<EnderecoDto>> findAllDto() {
		return new ResponseEntity<>(enderecoService.findAllDto(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<EnderecoDto> findByIdDto(@PathVariable Integer id) {
		EnderecoDto enderecoDtoEncontrado = enderecoService.findByIdDto(id);
		if (enderecoDtoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(enderecoDtoEncontrado,HttpStatus.OK);
		}
	}
}
