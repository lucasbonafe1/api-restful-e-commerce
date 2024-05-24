package br.com.projetofinal.cordeirostyle.dtos;

public class ItemPedidoDto {

	private Integer id_item_pedido;
	private Integer quantidade;
	private double preco_venda;
	private double percentual_desconto;
	private double valor_bruto;
	private double valor_liquido;
	private ProdutoDtoRetorno produto;

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

	public ProdutoDtoRetorno getProduto() {
		return produto;
	}

	public void setProdutoDto(ProdutoDtoRetorno produtoDto) {
		this.produto = produtoDto;
	}

	public Integer getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Integer id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}
	
	

}