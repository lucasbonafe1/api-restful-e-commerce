package br.com.projetofinal.cordeirostyle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.User;
import br.com.projetofinal.cordeirostyle.entities.UserDetailImpl;
import br.com.projetofinal.cordeirostyle.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(
				()-> new RuntimeException("Usuário não encontrado")
		);
		
		return new UserDetailImpl(user);
	}

}
