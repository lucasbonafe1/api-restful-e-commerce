package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetofinal.cordeirostyle.dtos.CepDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDtoRetorno;
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

	public List<EnderecoDto> findAll() throws NoSuchElementException {
		List<Endereco> enderecos = enderecoRepository.findAll();
		List<EnderecoDto> enderecosDto = new ArrayList<>();

		if (enderecos.isEmpty()) {
			throw new NoSuchElementException("Não há endereços registrados!");
		}

		for (Endereco endereco : enderecos) {
			EnderecoDto enderecoDto = modelMapper.map(endereco, EnderecoDto.class);
			enderecosDto.add(enderecoDto);
		}

		return enderecosDto;
	}

	public EnderecoDto findById(@PathVariable Integer id) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Endereco com id correspondente não encontrado!"));
		EnderecoDto enderecoDto = modelMapper.map(endereco, EnderecoDto.class);
		return enderecoDto;
	}

	public EnderecoDtoRetorno save(@RequestBody CepDto cep) {
		CepDto enderecoConsultado = cepRestService.findUserByCepFromViaCep(cep.getCep());
		Endereco endereco = new Endereco();

		endereco.setCep(enderecoConsultado.getCep());
		endereco.setRua(enderecoConsultado.getLogradouro());
		endereco.setBairro(enderecoConsultado.getBairro());
		endereco.setCidade(enderecoConsultado.getLocalidade());
		endereco.setNumero(cep.getNumero());
		endereco.setComplemento(cep.getComplemento());
		endereco.setUf(enderecoConsultado.getUf());

		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(enderecoSalvo, EnderecoDtoRetorno.class);

		return enderecoDtoRetorno;
	}

	public EnderecoDtoRetorno update(@PathVariable Integer id, @RequestBody EnderecoDto enderecoNovo) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Endereco com id correspondente não encontrado!"));
		EnderecoDtoRetorno enderecoDtoRetorno = null;
		CepDto enderecoConsultado = cepRestService.findUserByCepFromViaCep(enderecoNovo.getCep());
		if (endereco != null) {
			endereco.setCep(enderecoConsultado.getCep());
			endereco.setRua(enderecoConsultado.getLogradouro());
			endereco.setBairro(enderecoConsultado.getBairro());
			endereco.setCidade(enderecoConsultado.getLocalidade());
			endereco.setNumero(enderecoNovo.getNumero());
			endereco.setComplemento(enderecoNovo.getComplemento());
			endereco.setUf(enderecoConsultado.getUf());
			
			enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);

			enderecoRepository.save(endereco);
		}
		return enderecoDtoRetorno;
	}

	public EnderecoDtoRetorno deleteById(Integer id) {
		Endereco enderecoDeletado = enderecoRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Endereco com id correspondente não encontrado!"));
		EnderecoDtoRetorno enderecoDtoDeletado = null;
		if (enderecoDeletado != null) {
			enderecoDtoDeletado = modelMapper.map(enderecoDeletado, EnderecoDtoRetorno.class);
			enderecoRepository.deleteById(id);
		}
		return enderecoDtoDeletado;
	}
}
