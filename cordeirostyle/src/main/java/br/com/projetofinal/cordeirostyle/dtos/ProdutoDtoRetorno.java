package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;

public class ProdutoDtoRetorno {

	private Integer id_produto;
	private String nome;
	private String descricao;
	private double valor_unitario;
	private LocalDate data_cadastro;
	private ImagemDtoRetorno imagem;
	private CategoriaDtoRetorno categoria;

	public ProdutoDtoRetorno() {
		super();
	}

	public ProdutoDtoRetorno(Integer id_produto, String nome, String descricao, double valor_unitario,
			LocalDate data_cadastro, CategoriaDtoRetorno categoria, ImagemDtoRetorno imagem) {
		super();
		this.id_produto = id_produto;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_unitario = valor_unitario;
		this.data_cadastro = data_cadastro;
		this.categoria = categoria;
		this.imagem = imagem;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

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

	public LocalDate getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public CategoriaDtoRetorno getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDtoRetorno categoria) {
		this.categoria = categoria;
	}

	public ImagemDtoRetorno getImagem() {
		return imagem;
	}

	public void setImagem(ImagemDtoRetorno imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return String.format(
				"ID = %d \n" +
				"                 nome = %s \n" +		
				"                 descricao = %s \n" +
				"                 valor_unitario = R$ %.2f \n", 
				id_produto, nome, descricao, valor_unitario);
	}

}
