package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.CategoriaDto;
import br.com.projetofinal.cordeirostyle.dtos.CategoriaDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<CategoriaDto> findAll() throws NoSuchElementException{
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDto> categoriasDto = new ArrayList<>();

		if(categorias.isEmpty()) {
			throw new NoSuchElementException("Não há categorias registradas!");
		}
		
		for (Categoria categoria : categorias) {
			CategoriaDto categoriaTransformada = modelMapper.map(categoria, CategoriaDto.class);
			List<Produto> produtos = categoria.getProduto();
			List<ProdutoDtoRetorno> produtosDto = new ArrayList<>();
			
			for (Produto produto : produtos) {
				produtosDto.add(modelMapper.map(produto, ProdutoDtoRetorno.class));
			}
			
			categoriaTransformada.setProdutoDto(produtosDto);
			categoriasDto.add(categoriaTransformada);
		}
		
		return categoriasDto;
	}

	public CategoriaDto findById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria não encontrada!"));
		CategoriaDto categoriaDto = null;
		List<ProdutoDtoRetorno> produtosDto = new ArrayList<>();
		
		if(categoria != null) {
			categoriaDto = modelMapper.map(categoria, CategoriaDto.class);
			List<Produto> produtos = categoria.getProduto();
			
			if(produtos != null) {
				for (Produto produto : produtos) {
					produtosDto.add(modelMapper.map(produto, ProdutoDtoRetorno.class));
				}
			}
			categoriaDto.setProdutoDto(produtosDto);
		}
		return categoriaDto;
	}

	public CategoriaDtoRetorno save(CategoriaDto categoriaDto) {
		Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		CategoriaDtoRetorno categoriaRetorno = modelMapper.map(categoriaSalva, CategoriaDtoRetorno.class);
		return categoriaRetorno;
	}

	public CategoriaDtoRetorno update(Integer id, CategoriaDto novaCategoriaDto) {
		Categoria categoriaAtualizada = categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria não encontrada!"));
		CategoriaDtoRetorno categoriaDtoAtualizada = null;
		if (categoriaAtualizada != null) {
				categoriaAtualizada.setNome(novaCategoriaDto.getNome());
				categoriaAtualizada.setDescricao(novaCategoriaDto.getDescricao());
				categoriaDtoAtualizada = modelMapper.map(categoriaAtualizada, CategoriaDtoRetorno.class);
				categoriaRepository.save(categoriaAtualizada);
		}
		return categoriaDtoAtualizada;
	}

	public CategoriaDtoRetorno deleteById(Integer id) {
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria não encontrada!"));
		CategoriaDtoRetorno categoriaDeletadaDto = null;
		if (categoriaDeletada != null) {
				categoriaDeletadaDto = modelMapper.map(categoriaDeletada, CategoriaDtoRetorno.class);
				categoriaRepository.deleteById(id);
		}
		return categoriaDeletadaDto;
	}

}