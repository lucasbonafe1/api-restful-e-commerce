package br.com.projetofinal.cordeirostyle.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_produto",
        scope = Produto.class
)

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id_produto;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "qtd_estoque")
	private int qtd_estoque;
	
	@Column(name = "data_cadastro")
	private LocalDate data_cadastro = LocalDate.now();
	
	@Column(name = "valor_unitario")
	private double valor_unitario;
	
	@Column(name = "imagem")
	private byte[] imagem;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	 @JsonIgnore // Adicione esta anotação para ignorar a serialização da lista itensPedidos
	    @OneToMany(mappedBy = "produto")
	    private List<ItemPedido> itensPedidos;

	public Produto(String nome, String descricao, int qtd_estoque,double valor_unitario,
			byte[] imagem,Categoria categoria
			) 
	{
		this.nome = nome;
		this.descricao = descricao;
		this.qtd_estoque = qtd_estoque;
		this.valor_unitario = valor_unitario;
		this.imagem = imagem;
		this.categoria = categoria;
	}

	public Produto() {}

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

	public int getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public LocalDate getData_cadastro() {
		return data_cadastro;
	}
	
	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}


	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	public void setItensPedidos(List<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}
	
	public List<ItemPedido> getItensPedidos() {
		return itensPedidos;
	}

}
