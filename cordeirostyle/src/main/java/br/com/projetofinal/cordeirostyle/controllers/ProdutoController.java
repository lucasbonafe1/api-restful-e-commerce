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

import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.services.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		return new ResponseEntity<>(produtoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Produto produtoEncontrado = produtoService.findById(id);
		if (produtoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(produtoEncontrado,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto>update(@PathVariable Integer id,@RequestBody Produto produtoNovo){
		Produto produtoUpdate = produtoService.update(id,produtoNovo);
		if(produtoUpdate != null) {
			 return new ResponseEntity<>(produtoUpdate, HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteById(@PathVariable Integer id) {
		Produto produtoDeletado = produtoService.findById(id);

		if (produtoDeletado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		produtoService.deleteById(id);
		return new ResponseEntity<>(produtoDeletado, HttpStatus.OK);
	}
	
	@GetMapping("/dto")
	public ResponseEntity<List<ProdutoDto>> findAllDto() {
		return new ResponseEntity<>(produtoService.findAllDto(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDto> findByIdDto(@PathVariable Integer id) {
		ProdutoDto produtoEncontrado = produtoService.findByIdDto(id);
		if (produtoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(produtoEncontrado,HttpStatus.OK);
		}
	}
}
