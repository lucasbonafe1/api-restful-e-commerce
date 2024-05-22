package br.com.projetofinal.cordeirostyle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer id_item_pedido;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "preco_venda")
	private double preco_venda;
	
	@Column(name = "percentual_desconto")
	private double percentual_desconto;
	
	@Column(name = "valor_bruto")
	private double valor_bruto;
	
	@Column(name = "valor_liquido")
	private double valor_liquido;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public Integer getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Integer id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public double getPercentual_desconto() {
		return percentual_desconto * 100;
	}

	public void setPercentual_desconto(double percentual_desconto) {
		this.percentual_desconto = percentual_desconto/100;
	}

	public double getValor_bruto() {
		return calcValorBruto();
	}

	public void setValor_bruto(double valor_bruto) {
		this.valor_bruto = calcValorBruto();
	}

	public double getValor_liquido() {
		return calcValorLiquido();
	}

	public void setValor_liquido(double valor_liquido) {
		this.valor_liquido = calcValorLiquido();
	}
	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	
	public double calcValorBruto() {
		double valorFinal = this.preco_venda*this.quantidade;
		return valorFinal;
	}
	
	public double calcValorLiquido() {
		double valorBruto = calcValorBruto();
		double valorDesconto = valorBruto * this.percentual_desconto;
		double valorLiquido = valorBruto - valorDesconto;
		return valorLiquido;
	}
	

}
