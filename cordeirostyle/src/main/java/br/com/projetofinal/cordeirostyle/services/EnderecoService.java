package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetofinal.cordeirostyle.dtos.CepDto;
import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CepRestService cepRestService;
	
	public List<Endereco> findAll(){
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(@PathVariable Integer id){
		return enderecoRepository.findById(id).orElse(null);
	}
	
	public Endereco save(@RequestBody CepDto cep){
		CepDto enderecoConsultado = cepRestService.findUserByCepFromViaCep(cep.getCep());
		Endereco endereco = new Endereco();
		
		endereco.setCep(enderecoConsultado.getCep());
		endereco.setRua(enderecoConsultado.getLogradouro());
		endereco.setBairro(enderecoConsultado.getBairro());
		endereco.setCidade(enderecoConsultado.getLocalidade());
		endereco.setNumero(cep.getNumero());
		endereco.setComplemento(cep.getComplemento());
		endereco.setUf(enderecoConsultado.getUf());
		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco update(@PathVariable Integer id, @RequestBody Endereco enderecoNovo){
		Endereco endereco = enderecoRepository.findById(id).orElse(null);
		if (endereco != null) {
			try {
				endereco.setCep(enderecoNovo.getCep());
				endereco.setRua(enderecoNovo.getRua());
				endereco.setBairro(enderecoNovo.getBairro());
				endereco.setCidade(enderecoNovo.getCidade());
				endereco.setNumero(enderecoNovo.getNumero());
				endereco.setComplemento(enderecoNovo.getComplemento());
				endereco.setUf(enderecoNovo.getUf());
				
				enderecoRepository.save(endereco);
			} catch (Exception e) {
				System.out.println(e);
			  }
		}
		return endereco;
	}
	public Endereco deleteById(Integer id) {
		Endereco enderecoDeletado = enderecoRepository.findById(id).orElse(null); 
		
			if (enderecoDeletado != null) {
				try {
					enderecoRepository.deleteById(id);
					return enderecoDeletado;
				} catch (Exception e) {
					System.out.println(e);
				}
			
			}	
			return enderecoDeletado;
		}
	
	//FindAll
		public List<EnderecoDto> findAllDto() {
			List<Endereco> enderecos = enderecoRepository.findAll();
			List<EnderecoDto> enderecosDto = new ArrayList<>();
			
			for (Endereco endereco : enderecos) {
				EnderecoDto enderecosDtoAtual = modelMapper.map(endereco, EnderecoDto.class);
				
				ClienteDto clienteDto;
				Cliente cliente;
				
				cliente = endereco.getCliente();
				if (cliente != null) {
					 clienteDto = modelMapper.map(cliente, ClienteDto.class); 
					enderecosDtoAtual.setCliente(clienteDto);
					
				}
				enderecosDto.add(enderecosDtoAtual);
			} 
	        return enderecosDto;
	    	}
		//FindById
		
		public EnderecoDto findByIdDto(Integer id) {
			EnderecoDto enderecoDto = modelMapper.map(enderecoRepository.findById(id).orElse(null), EnderecoDto.class);
			return enderecoDto;
		}
		
		
	
}
