package br.com.projetofinal.cordeirostyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cordeirostyle.entities.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
