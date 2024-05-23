package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.CategoriaDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, Categoria novaCategoria) {
		Categoria categoriaAtualizada = categoriaRepository.findById(id).orElse(null);
		if (categoriaAtualizada != null) {
			try {
				categoriaAtualizada.setNome(novaCategoria.getNome());
				categoriaAtualizada.setDescricao(novaCategoria.getDescricao());
				categoriaRepository.save(categoriaAtualizada);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return categoriaAtualizada;
	}

	public Categoria deleteById(Integer id) {
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElse(null);
		if (categoriaDeletada != null) {
			try {
				categoriaRepository.deleteById(id);
				return categoriaDeletada;
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return categoriaDeletada;
	}

//DTOs:
	public List<CategoriaDto> findAllDto() {
		List<Categoria> categorias = categoriaRepository.findAll();
		List<CategoriaDto> categoriasDto = new ArrayList<>();

		for (Categoria categoria : categorias) {
			CategoriaDto categoriaTransformada = modelMapper.map(categoria, CategoriaDto.class);
			List<Produto> produtos = categoria.getProduto();
			List<ProdutoDto> produtosDto = new ArrayList<>();
			for (Produto produto : produtos) {
				produtosDto.add(modelMapper.map(produto, ProdutoDto.class));
			}
			categoriaTransformada.setProdutoDto(produtosDto);
			categoriasDto.add(categoriaTransformada);
		}
		return categoriasDto;
	}

	public CategoriaDto findByIdDto(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElse(null);
		CategoriaDto categoriaDto = new CategoriaDto();
		List<ProdutoDto> produtosDto = new ArrayList<>();
		
		if(categoria != null) {
			categoriaDto = modelMapper.map(categoria, CategoriaDto.class);
			List<Produto> produtos = categoria.getProduto();
			
			if(produtos != null) {
				for (Produto produto : produtos) {
					produtosDto.add(modelMapper.map(produto, ProdutoDto.class));
				}
			}
		}
		categoriaDto.setProdutoDto(produtosDto);
		return categoriaDto;
	}

}