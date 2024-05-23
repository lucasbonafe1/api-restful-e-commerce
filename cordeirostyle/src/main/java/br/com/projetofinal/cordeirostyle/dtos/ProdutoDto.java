package br.com.projetofinal.cordeirostyle.dtos;

import br.com.projetofinal.cordeirostyle.entities.Categoria;

public class ProdutoDto {

	private String nome;
	private String descricao;
	private double valor_unitario;
	private Categoria categoria;

	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public Integer getCategoria() {
		return categoria.getId_categoria();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	 
	@Override
	public String toString() {
		return "ProdutoDto [nome=" + nome + ", descricao=" + descricao + ", valor_unitario=" + valor_unitario
				+ "]";
	}
	
}
