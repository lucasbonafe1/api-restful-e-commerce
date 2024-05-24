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

import br.com.projetofinal.cordeirostyle.dtos.CepDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDtoRetorno;
import br.com.projetofinal.cordeirostyle.services.CepRestService;
import br.com.projetofinal.cordeirostyle.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	CepRestService cepRestService;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDto>> findAll() {
		return new ResponseEntity<>(enderecoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDto> findById(@PathVariable Integer id){
		EnderecoDto enderecoEncontrado = enderecoService.findById(id);
		return new ResponseEntity<>(enderecoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDtoRetorno> save(@RequestBody CepDto cep) {
		return new ResponseEntity<>(enderecoService.save(cep), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EnderecoDtoRetorno>update(@PathVariable Integer id,@RequestBody EnderecoDto enderecoNovo){
		return new ResponseEntity<>(enderecoService.update(id,enderecoNovo), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EnderecoDtoRetorno> deleteById(@PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.deleteById(id), HttpStatus.OK);
	}
	
}
