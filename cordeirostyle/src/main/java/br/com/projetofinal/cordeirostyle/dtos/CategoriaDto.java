package br.com.projetofinal.cordeirostyle.dtos;

import java.util.List;

import br.com.projetofinal.cordeirostyle.entities.Pedido;

public class CategoriaDto {
	private String nome;
	private String descricao;
	//private List<ProdutoDto> produtoDto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String categoria) {
		this.descricao = categoria;
	}
	
//	public List<ProdutoDto> getProdutoDto() {
//		return produtoDto;
//	}
//	public void setProdutoDto(List<ProdutoDto> produtoDto) {
//		this.produtoDto = produtoDto;
//	}
	
	
	@Override
	public String toString() {
		return "CategoriaDto [nome=" + nome + ", categoria=" + descricao + "]";
	}
	
	
}