package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<Endereco> findAll(){
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(@PathVariable Integer id){
		return enderecoRepository.findById(id).orElse(null);
	}
	
	public Endereco save(@RequestBody Endereco endereco){
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
