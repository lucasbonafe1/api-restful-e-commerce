package br.com.projetofinal.cordeirostyle.controllers;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.entities.Imagem;
import br.com.projetofinal.cordeirostyle.entities.Produto;
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
	
	@GetMapping("/imagem/{id}")
	public ResponseEntity<byte[]> buscaImagem(@PathVariable Integer id){
		Imagem imagem = produtoService.getImagem(id);
		if (imagem != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", imagem.getTipo());
			headers.add("Content-length", String.valueOf(imagem.getDados().length));
			headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
			
			return new ResponseEntity<>(imagem.getDados(), headers , HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, @RequestPart("produto") ProdutoDto produto) throws IOException {
		return new ResponseEntity<>(produtoService.save(produto,file), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestParam("file") MultipartFile file, @PathVariable Integer id, @RequestPart ProdutoDto produtoNovo) throws IOException{
		return new ResponseEntity<>(produtoService.update(id,produtoNovo,file), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.deleteById(id), HttpStatus.OK);
	}
}
