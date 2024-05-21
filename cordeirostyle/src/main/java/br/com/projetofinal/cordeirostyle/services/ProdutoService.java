package br.com.projetofinal.cordeirostyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Produto findById(@PathVariable Integer id){
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto save(@RequestBody Produto produto){
		return produtoRepository.save(produto);
	}
	public Produto update(@PathVariable Integer id, @RequestBody Produto produtoNovo){
		Produto produto = produtoRepository.findById(id).orElse(null);
		if (produto != null) {
			try {
				produto.setNome(produtoNovo.getNome());
				produto.setDescricao(produtoNovo.getDescricao());
				produto.setQtd_estoque(produtoNovo.getQtd_estoque());
				produto.setValor_unitario(produtoNovo.getValor_unitario());
				produto.setImagem(produtoNovo.getImagem());
				
				produtoRepository.save(produto);
			} catch (Exception e) {
				System.out.println(e);
			  }
		}
		return produto;
	}
	public Produto deleteById(Integer id) {
		Produto produtoDeletado = produtoRepository.findById(id).orElse(null); 
		
			if (produtoDeletado != null) {
				try {
					produtoRepository.deleteById(id);
					return produtoDeletado;
				} catch (Exception e) {
					System.out.println(e);
				}
			
			}	
			return produtoDeletado;
		}
	
}
