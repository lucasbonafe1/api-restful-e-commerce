package br.com.projetofinal.cordeirostyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.Categoria;
import br.com.projetofinal.cordeirostyle.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, Categoria novaCategoria) {
		Categoria categoriaAtualizada = categoriaRepository.findById(id).orElse(null);
		if(categoriaAtualizada != null) {
			try {
				categoriaAtualizada.setNome(novaCategoria.getNome());
				categoriaAtualizada.setDescricao(novaCategoria.getDescricao());
				categoriaRepository.save(categoriaAtualizada);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return categoriaAtualizada;
	}

	public Categoria deleteById(Integer id) {
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElse(null);
		if (categoriaDeletada != null) {
			try {
				categoriaRepository.deleteById(id);
				return categoriaDeletada;
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		return categoriaDeletada;
	}
	
}
