package br.com.projetofinal.cordeirostyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cordeirostyle.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
