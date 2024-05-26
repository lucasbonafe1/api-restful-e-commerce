
package br.com.projetofinal.cordeirostyle.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.projetofinal.cordeirostyle.entities.Imagem;
import br.com.projetofinal.cordeirostyle.repositories.ImagemRepository;
import br.com.projetofinal.cordeirostyle.repositories.ProdutoRepository;

@Service
public class ImagemService {

	@Autowired
	ImagemRepository imagemRepository;
	
	@Autowired 
	ProdutoRepository produtoRepository;
	
	
	public Imagem save(MultipartFile imagem) throws IOException {
		Imagem imagemSave = new Imagem();
		imagemSave.setDados(imagem.getBytes());
		return imagemRepository.save(imagemSave);
	}
}

