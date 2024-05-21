package br.com.projetofinal.cordeirostyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cordeirostyle.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
