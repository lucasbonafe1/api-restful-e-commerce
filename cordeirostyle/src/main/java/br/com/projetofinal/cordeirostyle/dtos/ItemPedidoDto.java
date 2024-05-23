package br.com.projetofinal.cordeirostyle.dtos;

public class ItemPedidoDto {

	private Integer quantidade;
	private double preco_venda;
	private double percentual_desconto;
	private double valor_bruto;
	private double valor_liquido;
	/*private ProdutoDto produtoDto;*/

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

	/*public ProdutoDto getProdutoDto() {
		return produtoDto;
	}

	public void setProdutoDto(ProdutoDto produtoDto) {
		this.produtoDto = produtoDto;
	}

	@Override
	public String toString() {
		return "ItemPedidoDto [quantidade=" + quantidade + ", preco_venda=" + preco_venda + ", percentual_desconto="
				+ percentual_desconto + ", valor_bruto=" + valor_bruto + ", valor_liquido=" + valor_liquido + "]";
	}*/

}