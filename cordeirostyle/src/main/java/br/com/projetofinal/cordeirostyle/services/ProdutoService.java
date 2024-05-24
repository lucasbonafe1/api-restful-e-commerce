package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.CategoriaRepository;
import br.com.projetofinal.cordeirostyle.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<ProdutoDto> findAll() throws NoSuchElementException{
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDto> produtosDto = new ArrayList<>();

		if(produtos.isEmpty()) {
			throw new NoSuchElementException("Não há produtos registrados!");
		}
		
		for (Produto produto : produtos) {
			ProdutoDto produtoTransformado = modelMapper.map(produto, ProdutoDto.class);
			produtosDto.add(produtoTransformado);
		}
		return produtosDto;
	}
	
	public ProdutoDto findById(Integer id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Produto não encontrado!"));
		ProdutoDto produtoDto = null;
		
		if(produto != null) {
			produtoDto = modelMapper.map(produto, ProdutoDto.class);
		}
		
		return produtoDto;
	}
	
	public ProdutoDtoRetorno save(@RequestBody ProdutoDto produtoDto){
		Produto produto = modelMapper.map(produtoDto, Produto.class);
		Produto produtoSalvo = produtoRepository.save(produto);
		ProdutoDtoRetorno produtoRetorno = modelMapper.map(produtoSalvo, ProdutoDtoRetorno.class);
		return produtoRetorno;
	}
	
	public ProdutoDto update(@PathVariable Integer id, @RequestBody ProdutoDto produtoNovoDto){
		Produto produtoAtualizado = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto não encontrado!"));
		ProdutoDto produtoDtoAtualizado = null;
		if (produtoAtualizado != null) {
				produtoAtualizado.setNome(produtoNovoDto.getNome());
				produtoAtualizado.setDescricao(produtoNovoDto.getDescricao());
				produtoAtualizado.setQtd_estoque(produtoNovoDto.getQtd_estoque());
				produtoAtualizado.setValor_unitario(produtoNovoDto.getValor_unitario());
				produtoAtualizado.setData_cadastro(produtoNovoDto.getData_cadastro());
				produtoAtualizado.setCategoria(categoriaRepository.findById(produtoNovoDto.getCategoria().getId_categoria()).orElseThrow(() -> new NoSuchElementException("Categoria não encontrado!")));
				produtoDtoAtualizado = modelMapper.map(produtoAtualizado, ProdutoDto.class);
				
				produtoRepository.save(produtoAtualizado);
		}
		return produtoDtoAtualizado;
	}
	public ProdutoDtoRetorno deleteById(Integer id) {
		Produto produtoDeletado = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto não encontrado!"));
		ProdutoDtoRetorno produtoDtoDeletado = null;
		if (produtoDeletado != null) {
			produtoDtoDeletado = modelMapper.map(produtoDeletado, ProdutoDtoRetorno.class);
			produtoRepository.deleteById(id);
		}	
		return produtoDtoDeletado;
	}
}
