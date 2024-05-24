package br.com.projetofinal.cordeirostyle.dtos;

import java.util.List;

public class CategoriaDto {
	private Integer id_categoria;
	private String nome;
	private String descricao;
	private List<ProdutoDto> produtoDto;
	
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
	
	public List<ProdutoDto> getProdutoDto() {
		return produtoDto;
	}
	public void setProdutoDto(List<ProdutoDto> produtoDto) {
		this.produtoDto = produtoDto;
	}
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	
	@Override
	public String toString() {
		return "CategoriaDto [nome=" + nome + ", categoria=" + descricao + "]";
	}
	
	
}