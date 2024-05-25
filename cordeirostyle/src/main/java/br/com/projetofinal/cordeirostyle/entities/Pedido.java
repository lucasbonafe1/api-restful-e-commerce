package br.com.projetofinal.cordeirostyle.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_pedido",
        scope = Pedido.class
)
@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pedido;
	
	@Column(name = "data_pedido")
	private LocalDate data_pedido = LocalDate.now();
	
	//@Future
	@Column(name = "data_entrega")
	private LocalDate data_entrega;
	
	@Column(name = "data_envio")
	private LocalDate data_envio;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "valor_total")
	private double valor_total;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente") 
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedidos;

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
		return calcValorTotal();
	}

	public void setValor_total(double valor_total) {
		this.valor_total = calcValorTotal();
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemPedido> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(List<ItemPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}
	// Tem que criar categoria, produto, pedido e item pedido
	public double calcValorTotal() {
		double valorTotal = 0;
		try {
			for (ItemPedido itemPedido : itensPedidos) {
				valorTotal += itemPedido.getValor_liquido();
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return valorTotal;
	}
	
}
