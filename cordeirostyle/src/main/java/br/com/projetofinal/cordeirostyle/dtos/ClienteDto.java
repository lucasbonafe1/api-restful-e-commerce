package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;

public class ClienteDto {
	
	private Integer id_cliente;
	private String email;
	private String nome_completo;
	private String telefone;
	private LocalDate data_nascimento;
	private EnderecoDtoRetorno endereco;
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public EnderecoDtoRetorno getEndereco() {
		return endereco;
	}
	public void setEnderecoDto(EnderecoDtoRetorno endereco) {
		this.endereco = endereco;
	}
	
	
}