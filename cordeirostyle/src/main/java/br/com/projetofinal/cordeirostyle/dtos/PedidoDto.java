package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;
import java.util.List;

public class PedidoDto {
	private Integer id_pedido;
	private LocalDate data_pedido;
	private LocalDate data_entrega;
	private LocalDate data_envio;
	private Boolean status;
	private Double valor_total;
	private ClienteDtoRetorno cliente;
	private List<ItemPedidoDto> itens;
	
	public PedidoDto() {
		super();
	}

	public PedidoDto(Integer id_pedido, LocalDate data_pedido, LocalDate data_entrega, LocalDate data_envio,
			Boolean status, Double valor_total, List<ItemPedidoDto> itens, ClienteDtoRetorno cliente) {
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

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	
	public void setCliente(ClienteDtoRetorno cliente) {
		this.cliente = cliente;
	}

	public ClienteDtoRetorno getCliente() {
		return cliente;
	}

	public List<ItemPedidoDto> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDto> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "PedidoDto [ data_pedido=" + data_pedido + ", data_entrega=" + data_entrega
				+ ", data_envio=" + data_envio + ", status=" + status + ", valor_total=" + valor_total + ", cliente="
				+ cliente + "]";
	}	

}
