package br.com.projetofinal.cordeirostyle.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.projetofinal.cordeirostyle.dtos.CategoriaDtoRetorno;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDtoRetorno;
import br.com.projetofinal.cordeirostyle.entities.Imagem;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.CategoriaRepository;
import br.com.projetofinal.cordeirostyle.repositories.ImagemRepository;
import br.com.projetofinal.cordeirostyle.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ImagemRepository imagemRepository;

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
	
	public ProdutoDto save(@RequestBody ProdutoDto produtoDto,MultipartFile file) throws IOException{
		Produto produto = modelMapper.map(produtoDto, Produto.class);
		Produto produtoSalvo = new Produto();
		produtoSalvo = produtoRepository.save(produto);
		
		Imagem imagem = new Imagem();
		imagem.setDados(file.getBytes());
		imagem.setTipo(file.getContentType());
		imagem.setId_imagem(produto.getId_produto());
		imagem.setProduto(produtoSalvo);
		imagemRepository.save(imagem);
		produtoSalvo.setImagem(imagem);
		
		produtoSalvo = produtoRepository.save(produto);
		
		ProdutoDto produtoRetorno = modelMapper.map(produtoSalvo, ProdutoDto.class);
		CategoriaDtoRetorno categoriaDtoRetorno = modelMapper.map(categoriaRepository.findById(produtoDto.getCategoria()
				.getId_categoria()).orElseThrow(()-> new NoSuchElementException
				("Categoria não encontrada!")), CategoriaDtoRetorno.class);
	
		produtoRetorno.setCategoria(categoriaDtoRetorno);
		return produtoRetorno;
	}
	
	public ProdutoDto update(Integer id, @RequestBody ProdutoDto produtoNovoDto, MultipartFile file) throws IOException{
		Produto produtoAtualizado = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Produto não encontrado!"));
		ProdutoDto produtoDtoAtualizado = null;
		if (produtoAtualizado != null) {
			
				Imagem imagem = new Imagem();
				imagem.setDados(file.getBytes());
				imagem.setTipo(file.getContentType());
				imagem.setId_imagem(produtoAtualizado.getId_produto());
				imagem.setProduto(produtoAtualizado);
				imagemRepository.save(imagem);
				
				produtoAtualizado.setImagem(imagem);
				produtoAtualizado.setNome(produtoNovoDto.getNome());
				produtoAtualizado.setDescricao(produtoNovoDto.getDescricao());
				produtoAtualizado.setQtd_estoque(produtoNovoDto.getQtd_estoque());
				produtoAtualizado.setValor_unitario(produtoNovoDto.getValor_unitario());
				produtoAtualizado.setData_cadastro(produtoNovoDto.getData_cadastro());
				produtoAtualizado.setCategoria(categoriaRepository.findById(produtoNovoDto.getCategoria().getId_categoria()).orElseThrow(() -> new NoSuchElementException("Categoria não encontrada!")));
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
	
	public Imagem getImagem(Integer id) throws NoSuchElementException{
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return produto.get().getImagem();
        }
        throw new NoSuchElementException("Este produto não existe ou não possui imagem!");
    }
	
}
