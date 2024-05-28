package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;
import java.util.List;

public class RelatorioPedidoDto {

	private Integer id_pedido;
	private LocalDate data_pedido;
	private Double valor_total;
	
	private List<ItemPedidoDto> itensPedido;

	public RelatorioPedidoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public List<ItemPedidoDto> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDto> itensPedido) {
		this.itensPedido = itensPedido;
	}
	
	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append(String.format(// append serve pra concatenar para o string build
	        "=====================================\n" +
	        "Pedido ID      : %d\n" +
	        "Data do Pedido : %s\n" +
	        "Valor Total    : R$ %.2f\n",
	        id_pedido, data_pedido, valor_total
	    ));

	    for (ItemPedidoDto item : itensPedido) { // com o builder ele deixa acrescentar um for dentro do toString
	        builder.append(String.format(
	            "-------------------------------------\n" +
	            "Item Pedido ID : %d\n" +
	            "Produto        : %s\n" +
	            "Preço de Venda : R$ %.2f\n" +
	            "Quantidade     : %d\n" +
	            "Desconto       : %.2f%%\n" +
	            "Valor Bruto    : R$ %.2f\n" +
	            "Valor Líquido  : R$ %.2f\n" +
	            "=====================================\n",
	            item.getId_item_pedido(), item.getProduto(), item.getPreco_venda(),
	            item.getQuantidade(), item.getPercentual_desconto(),
	            item.getValor_bruto(), item.getValor_liquido()
	        ));
	    }
	    
	    return builder.toString();
	}
	


}
