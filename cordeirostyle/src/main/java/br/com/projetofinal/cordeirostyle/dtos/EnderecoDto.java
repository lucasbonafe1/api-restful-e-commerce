package br.com.projetofinal.cordeirostyle.dtos;

public class EnderecoDto {
	private Integer id_endereco;
	private String cep;
	private String rua;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private ClienteDtoRetorno cliente;
	
	public Integer getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public ClienteDtoRetorno getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDtoRetorno cliente) {
		this.cliente = cliente;
	}
	
}
