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

import br.com.projetofinal.cordeirostyle.dtos.CategoriaDto;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		return new ResponseEntity<>(categoriaService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria categoria = categoriaService.findById(id);
		
		if(categoria == null) {
			return new ResponseEntity<>(categoria, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(categoria, HttpStatus.OK);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(categoriaService.save(categoria),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria>update(@PathVariable Integer id, @RequestBody Categoria novaCategoria) {
		Categoria categoriaUpdate = categoriaService.findById(id);
		if(categoriaUpdate != null) {
			return new ResponseEntity<>(categoriaService.update(id, novaCategoria), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(categoriaUpdate, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> deleteById(@PathVariable Integer id){
		Categoria categoria = categoriaService.findById(id);
		if(categoria == null) {
			return new ResponseEntity<>(categoria, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoriaService.deleteById(id), HttpStatus.OK);
		}
	}
	
//DTOs:
	@GetMapping("/dto")
	public ResponseEntity<List<CategoriaDto>> findAllDto() {
		return new ResponseEntity<>(categoriaService.findAllDto(), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<CategoriaDto> findByIdDto(@PathVariable Integer id) {
		CategoriaDto categoriaEncontrada = categoriaService.findByIdDto(id);
		if (categoriaEncontrada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoriaEncontrada,HttpStatus.OK);
		}
	}

}
