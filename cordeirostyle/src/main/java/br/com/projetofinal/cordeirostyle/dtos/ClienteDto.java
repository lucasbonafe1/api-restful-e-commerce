package br.com.projetofinal.cordeirostyle.dtos;



public class ClienteDto {

	private String email;
	private String nome_completo;
	private String telefone;
	private String data_nascimento;
	private EnderecoDto endereco;
	
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
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getEnderecoDto() {
		return endereco.getCep();
	}
	public void setEnderecoDto(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	
	
}