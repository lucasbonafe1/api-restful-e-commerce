package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.dtos.CategoriaDto;
import br.com.projetofinal.cordeirostyle.dtos.ClienteDto;
import br.com.projetofinal.cordeirostyle.dtos.EnderecoDto;
import br.com.projetofinal.cordeirostyle.dtos.ItemPedidoDto;
import br.com.projetofinal.cordeirostyle.dtos.ProdutoDto;
import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.entities.Endereco;
import br.com.projetofinal.cordeirostyle.entities.ItemPedido;
import br.com.projetofinal.cordeirostyle.entities.Produto;
import br.com.projetofinal.cordeirostyle.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ModelMapper modelMapper;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, Cliente novocliente) {
		Cliente clienteAtualizada = clienteRepository.findById(id).orElse(null);
		if(clienteAtualizada != null) {
			try {
				clienteAtualizada.setNome_completo(novocliente.getNome_completo());
				clienteAtualizada.setEmail(novocliente.getEmail());
				clienteAtualizada.setCpf(novocliente.getCpf());
				clienteAtualizada.setTelefone(novocliente.getTelefone());
				clienteAtualizada.setData_nascimento(novocliente.getData_nascimento());
				clienteRepository.save(clienteAtualizada);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return clienteAtualizada;
	}

	public Cliente deleteById(Integer id) {
		Cliente clienteDeletada = clienteRepository.findById(id).orElse(null);
		if (clienteDeletada != null) {
			try {
				clienteRepository.deleteById(id);
				return clienteDeletada;
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return clienteDeletada;
	}
	
	public List<ClienteDto> findAllDto() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDto> clienteDto = new ArrayList<>();
		Endereco endereco;
		EnderecoDto enderecoDto;
		
		for (Cliente cliente : clientes) {
			ClienteDto clienteTranformado = modelMapper.map(cliente, ClienteDto.class);
			
			endereco = cliente.getEndereco();
			if (endereco!= null) {
				enderecoDto = modelMapper.map(endereco,EnderecoDto.class);
				clienteTranformado.setEnderecoDto(enderecoDto);				
			}
			clienteDto.add(clienteTranformado);			
		}
        return clienteDto;
    }
	
	
	public ClienteDto findByIdDto(Integer id) {
		Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
		ClienteDto clienteDto = modelMapper.map(clienteEncontrado, ClienteDto.class);
		
		Endereco endereco = clienteEncontrado.getEndereco();
		if (endereco!= null) {
			EnderecoDto enderecoDto = modelMapper.map(endereco, EnderecoDto.class);
			clienteDto.setEnderecoDto(enderecoDto);
		}
		return clienteDto;
	}
	
}
