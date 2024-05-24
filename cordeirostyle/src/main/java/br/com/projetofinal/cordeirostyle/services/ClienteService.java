package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDtoRetorno;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	ModelMapper modelMapper;

	public List<ClienteDto> findAll() throws NoSuchElementException {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDto> clientesDto = new ArrayList<>();

		if (clientes.isEmpty()) {
			throw new NoSuchElementException("Não há clientes registrados!");
		}

		for (Cliente cliente : clientes) {
			ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
			Endereco endereco = cliente.getEndereco();
			EnderecoDtoRetorno enderecoDtoRetorno = null;
			if (endereco != null) {
				enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);
			}

			clienteDto.setEnderecoDto(enderecoDtoRetorno);
			clientesDto.add(clienteDto);
		}

		return clientesDto;
	}

	public ClienteDto findById(Integer id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Cliente com id correspondente não encontrado!"));
		ClienteDto clienteDto = null;

		if (cliente.getEndereco() != null) {
			Endereco endereco = cliente.getEndereco();
			EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);
			clienteDto = modelMapper.map(cliente, ClienteDto.class);
			clienteDto.setEnderecoDto(enderecoDtoRetorno);
		}

		return clienteDto;
	}

	public ClienteDto save(ClienteDto clienteDto) {
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
		Cliente clienteSalvo = clienteRepository.save(cliente);
		ClienteDto clienteSalvoDto = modelMapper.map(clienteSalvo, ClienteDto.class);

		EnderecoDto enderecoDto = enderecoService.findById(clienteDto.getEndereco().getId_endereco());
		Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);
		EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);
		clienteSalvoDto.setEnderecoDto(enderecoDtoRetorno);

		return clienteSalvoDto;
	}

	public ClienteDto update(Integer id, ClienteDto novoclienteDto) {
		Cliente clienteAtualizado = clienteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Cliente com id correspondente não encontrado!"));
		ClienteDto clienteDtoAtualizado = null;
		if (clienteAtualizado != null) {
			EnderecoDto enderecoDto = enderecoService.findById(novoclienteDto.getEndereco().getId_endereco());
			Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);
			EnderecoDtoRetorno enderecoDtoRetorno = modelMapper.map(endereco, EnderecoDtoRetorno.class);

			clienteAtualizado.setNome_completo(novoclienteDto.getNome_completo());
			clienteAtualizado.setEmail(novoclienteDto.getEmail());
			clienteAtualizado.setTelefone(novoclienteDto.getTelefone());
			clienteAtualizado.setData_nascimento(novoclienteDto.getData_nascimento());
			clienteAtualizado.setEndereco(endereco);

			clienteDtoAtualizado = modelMapper.map(clienteAtualizado, ClienteDto.class);
			clienteDtoAtualizado.setEnderecoDto(enderecoDtoRetorno);

			clienteRepository.save(clienteAtualizado);
		}
		return clienteDtoAtualizado;
	}

	public ClienteDto deleteById(Integer id) {
		Cliente clienteDeletado = clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente com id correspondente não encontrado!"));
		ClienteDto clienteDtoDeletado = null;
		if (clienteDeletado != null) {
			clienteDtoDeletado = modelMapper.map(clienteDeletado, ClienteDto.class);
			clienteRepository.deleteById(id);
		}
		return clienteDtoDeletado;
	}
}
