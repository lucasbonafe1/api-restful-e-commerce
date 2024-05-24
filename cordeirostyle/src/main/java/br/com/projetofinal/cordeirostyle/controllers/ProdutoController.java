package br.com.projetofinal.cordeirostyle.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.services.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> findAll() {
		return new ResponseEntity<>(produtoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		ProdutoDto produtoEncontrado = produtoService.findById(id);
		return new ResponseEntity<>(produtoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProdutoDto produto) {
		return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@PathVariable Integer id,@RequestBody ProdutoDto produtoNovo){
		return new ResponseEntity<>(produtoService.update(id,produtoNovo), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.deleteById(id), HttpStatus.OK);
	}
}
