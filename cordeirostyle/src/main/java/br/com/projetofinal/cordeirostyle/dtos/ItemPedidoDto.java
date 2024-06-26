package br.com.projetofinal.cordeirostyle.dtos;

public class ItemPedidoDto {

	private Integer id_item_pedido;
	private int quantidade;
	private double preco_venda;
	private double percentual_desconto;
	private double valor_bruto;
	private double valor_liquido;
	private PedidoDtoRetorno pedido;
	private ProdutoDtoRetorno produto;

	public ItemPedidoDto() {
		super();
	}

	public ItemPedidoDto(Integer id_item_pedido, int quantidade, double preco_venda, double percentual_desconto,
			double valor_bruto, double valor_liquido, ProdutoDtoRetorno produto) {
		super();
		this.id_item_pedido = id_item_pedido;
		this.quantidade = quantidade;
		this.preco_venda = preco_venda;
		this.percentual_desconto = percentual_desconto;
		this.valor_bruto = valor_bruto;
		this.valor_liquido = valor_liquido;
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
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

	public PedidoDtoRetorno getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDtoRetorno pedido) {
		this.pedido = pedido;
	}
	
	

}