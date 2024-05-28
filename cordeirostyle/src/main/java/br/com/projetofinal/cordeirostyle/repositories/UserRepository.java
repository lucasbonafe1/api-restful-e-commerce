package br.com.projetofinal.cordeirostyle.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetofinal.cordeirostyle.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}
