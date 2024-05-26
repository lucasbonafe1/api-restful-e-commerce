package br.com.projetofinal.cordeirostyle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagem")
public class Imagem {

	@Id
	@Column(name = "id_imagem")
	private Integer id_imagem;
	
	@Lob
	@Column(name = "dados")
	private byte[] dados;
	
	@Column(name = "tipo")
	private String tipo;

	@OneToOne
	private Produto produto;
	
	public Integer getId_imagem() {
		return id_imagem;
	}

	public void setId_imagem(Integer id_imagem) {
		this.id_imagem = id_imagem;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
		
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}