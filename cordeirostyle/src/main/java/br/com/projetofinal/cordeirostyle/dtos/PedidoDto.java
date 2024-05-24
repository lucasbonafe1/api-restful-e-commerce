package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;

public class PedidoDto {

	private LocalDate data_pedido;
	private LocalDate data_entrega;
	private LocalDate data_envio;
	private Boolean status;
	private Double valor_total;
	private ClienteDto cliente;
	private ItemPedidoDto itens;

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public LocalDate getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(LocalDate data_entrega) {
		this.data_entrega = data_entrega;
	}

	public LocalDate getData_envio() {
		return data_envio;
	}

	public void setData_envio(LocalDate data_envio) {
		this.data_envio = data_envio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	
	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}	
	
	public ItemPedidoDto getItens() {
		return itens;
	}

	public void setItens(ItemPedidoDto itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "PedidoDto [ data_pedido=" + data_pedido + ", data_entrega=" + data_entrega
				+ ", data_envio=" + data_envio + ", status=" + status + ", valor_total=" + valor_total + ", cliente="
				+ cliente + "]";
	}	

}
