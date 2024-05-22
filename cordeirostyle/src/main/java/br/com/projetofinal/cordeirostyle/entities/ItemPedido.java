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
	private Float preco_venda;
	
	@Column(name = "percentual_desconto")
	private Float percentual_desconto;
	
	@Column(name = "valor_bruto")
	private Float valor_bruto;
	
	@Column(name = "valor_liquido")
	private Float valor_liquido;
	
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

	public Float getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(Float preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Float getPercentual_desconto() {
		return percentual_desconto * 100;
	}

	public void setPercentual_desconto(Float percentual_desconto) {
		this.percentual_desconto = percentual_desconto/100;
	}

	public Float getValor_bruto() {
		return calcValorBruto();
	}

	public void setValor_bruto(Float valor_bruto) {
		this.valor_bruto = calcValorBruto();
	}

	public Float getValor_liquido() {
		return calcValorLiquido();
	}

	public void setValor_liquido(Float valor_liquido) {
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

	
	
	public Float calcValorBruto() {
		Float valorFinal = this.preco_venda*this.quantidade;
		return valorFinal;
	}
	
	public Float calcValorLiquido() {
		Float valorBruto = calcValorBruto();
		Float valorDesconto = valorBruto * this.percentual_desconto;
		Float valorLiquido = valorBruto - valorDesconto;
		return valorLiquido;
	}
	

}
