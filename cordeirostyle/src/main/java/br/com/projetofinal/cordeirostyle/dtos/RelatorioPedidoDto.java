package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;
import java.util.List;

public class RelatorioPedidoDto {

	private Integer id_pedido;
	private LocalDate data_pedido;
	private Double valor_total;
	
	private Integer id_item_pedido;
	private String produto;
	private double preco_venda;
	private Integer quantidade;
	private double percentual_desconto;
	private double valor_bruto;
	private double valor_liquido;

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Integer getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Integer id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPercentual_desconto() {
		return percentual_desconto;
	}

	public void setPercentual_desconto(double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}

	public double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "=====================================\n" +
	        "Pedido ID      : %d\n" +
	        "Data do Pedido : %s\n" +
	        "Valor Total    : R$ %.2f\n" +
	        "-------------------------------------\n" +
	        "Item Pedido ID : %d\n" +
	        "Produto        : %s\n" +
	        "Preço de Venda : R$ %.2f\n" +
	        "Quantidade     : %d\n" +
	        "Desconto       : %.2f%%\n" +
	        "Valor Bruto    : R$ %.2f\n" +
	        "Valor Líquido  : R$ %.2f\n" +
	        "=====================================\n",
	        id_pedido, data_pedido, valor_total,
	        id_item_pedido, produto, preco_venda, quantidade, percentual_desconto, valor_bruto, valor_liquido
	    );
	}


}
