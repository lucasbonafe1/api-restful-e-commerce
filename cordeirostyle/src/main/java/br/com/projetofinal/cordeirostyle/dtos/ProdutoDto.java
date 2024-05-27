package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;

public class ProdutoDto {

	private Integer id_produto;
	private String nome;
	private String descricao;
	private double valor_unitario;
	private int qtd_estoque;
	private LocalDate data_cadastro = LocalDate.now();
	private CategoriaDtoRetorno categoria;
	

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

	public CategoriaDtoRetorno getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDtoRetorno categoria) {
		this.categoria = categoria;
	}
	 
	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public int getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	

	public LocalDate getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}


	@Override
	public String toString() {
		return "ProdutoDto [nome=" + nome + ", descricao=" + descricao + ", valor_unitario=" + valor_unitario
				+ "]";
	}
	
}
