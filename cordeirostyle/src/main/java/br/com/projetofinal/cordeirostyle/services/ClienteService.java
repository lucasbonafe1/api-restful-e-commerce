package br.com.projetofinal.cordeirostyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.entities.Cliente;
import br.com.projetofinal.cordeirostyle.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

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
	
}
