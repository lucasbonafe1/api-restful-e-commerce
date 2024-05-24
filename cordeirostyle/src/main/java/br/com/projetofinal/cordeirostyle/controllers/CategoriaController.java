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
import br.com.projetofinal.cordeirostyle.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll() {
		return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		CategoriaDto categoriaEncontrada = categoriaService.findById(id);
		return new ResponseEntity<>(categoriaEncontrada,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CategoriaDto categoria) {
		return new ResponseEntity<>(categoriaService.save(categoria),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@PathVariable Integer id, @RequestBody CategoriaDto novaCategoriaDto) {
		CategoriaDto categoriaUpdate = categoriaService.findById(id);
		if(categoriaUpdate != null) {
			return new ResponseEntity<>(categoriaService.update(id, novaCategoriaDto), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("{\"error\": \"Categoria Não Encontrada\"}",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		CategoriaDto categoria = categoriaService.findById(id);
		if(categoria == null) {
			return new ResponseEntity<>("{\"error\": \"Categoria Não Encontrada\"}", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoriaService.deleteById(id), HttpStatus.OK);
		}
	}
	
//DTOs:
	
	
	

}
