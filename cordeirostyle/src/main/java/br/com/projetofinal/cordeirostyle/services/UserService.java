package br.com.projetofinal.cordeirostyle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetofinal.cordeirostyle.entities.Role;
import br.com.projetofinal.cordeirostyle.entities.RoleEnum;
import br.com.projetofinal.cordeirostyle.entities.User;
import br.com.projetofinal.cordeirostyle.entities.UserDetailImpl;
import br.com.projetofinal.cordeirostyle.records.CredenciaisLoginRecord;
import br.com.projetofinal.cordeirostyle.records.JwtTokenRecord;
import br.com.projetofinal.cordeirostyle.records.UserRecord;
import br.com.projetofinal.cordeirostyle.repositories.UserRepository;


@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
	PasswordEncoder encoder;

    
    public JwtTokenRecord authenticateUser(CredenciaisLoginRecord credenciaisLoginRecord) {
        
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
		            new UsernamePasswordAuthenticationToken(credenciaisLoginRecord.email(), credenciaisLoginRecord.password());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
		
		return new JwtTokenRecord(jwtTokenService.generateToken(userDetails));
    }

    public User createUser(UserRecord userRecord) {

    	Set<String> strRoles = userRecord.role();
    	List<Role> roles = new ArrayList<>();
    	
    	for(String strRole : strRoles) {
    		Role role = new Role(RoleEnum.valueOf(strRole));
    		roles.add(role);
    	}
    	
        User newUser = new User(userRecord.email(),
					encoder.encode(userRecord.password()),
					roles
				);

        return userRepository.save(newUser);
    }
}