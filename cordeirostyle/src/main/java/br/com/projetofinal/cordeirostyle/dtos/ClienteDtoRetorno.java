package br.com.projetofinal.cordeirostyle.dtos;

import java.time.LocalDate;

public class ClienteDtoRetorno {

	private Integer id_cliente;
	private String email;
	private String nome_completo;
	private String telefone;
	private LocalDate data_nascimento;
	private EnderecoDtoRetorno enderecoDtoRetorno;

	public ClienteDtoRetorno() {
	}

	public ClienteDtoRetorno(Integer id_cliente, String email, String nome_completo, String telefone,
			LocalDate data_nascimento, EnderecoDtoRetorno enderecoDtoRetorno) {
		super();
		this.id_cliente = id_cliente;
		this.email = email;
		this.nome_completo = nome_completo;
		this.telefone = telefone;
		this.data_nascimento = data_nascimento;
		this.enderecoDtoRetorno = enderecoDtoRetorno;
	}

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

	public EnderecoDtoRetorno getEnderecoDtoRetorno() {
		return enderecoDtoRetorno;
	}

	public void setEnderecoDtoRetorno(EnderecoDtoRetorno enderecoDtoRetorno) {
		this.enderecoDtoRetorno = enderecoDtoRetorno;
	}

}