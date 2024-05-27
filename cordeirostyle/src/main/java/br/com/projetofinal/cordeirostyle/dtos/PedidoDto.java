package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;
import java.util.List;

import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.ItemPedido;

public class PedidoDto {
	private Integer id_pedido;
	private LocalDate data_pedido;
	private LocalDate data_entrega;
	private LocalDate data_envio;
	private Boolean status;
	private double valor_total;
	private Cliente cliente;
	private List<ItemPedido> itens;
	
	public PedidoDto() {
		super();
	}

	public PedidoDto(Integer id_pedido, LocalDate data_pedido, LocalDate data_entrega, LocalDate data_envio,
			Boolean status, double valor_total, List<ItemPedido> itens, Cliente cliente) {
		super();
		this.id_pedido = id_pedido;
		this.data_pedido = data_pedido;
		this.data_entrega = data_entrega;
		this.data_envio = data_envio;
		this.status = status;
		this.valor_total = valor_total;
		this.itens = itens;
		this.cliente = cliente;
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

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "PedidoDto [ data_pedido=" + data_pedido + ", data_entrega=" + data_entrega
				+ ", data_envio=" + data_envio + ", status=" + status + ", valor_total=" + valor_total + ", cliente="
				+ cliente + "]";
	}	

}
