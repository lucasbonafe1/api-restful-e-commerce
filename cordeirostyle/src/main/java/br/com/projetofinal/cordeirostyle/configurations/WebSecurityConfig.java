package br.com.projetofinal.cordeirostyle.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.projetofinal.cordeirostyle.filters.AuthenticationFilter;
import br.com.projetofinal.cordeirostyle.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/health-check", 
                    		"/auth/**", 
                    		"/api/roles/**",
                    		"/swagger-ui/**",
                    		"/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/produto**", "/categoria**").hasAnyRole("ADMIN","USER")
                 
                    .requestMatchers(HttpMethod.GET, "/endereco**", "/cliente**", "/item_pedido**", "/pedido**").hasRole("USER")
                    .requestMatchers(HttpMethod.POST, "/endereco**", "/cliente**", "/item_pedido**", "/pedido**").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/endereco**", "/cliente**", "/item_pedido**", "/pedido**").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE, "/endereco**", "/cliente**", "/item_pedido**", "/pedido**").hasRole("USER")  
                    
                    .requestMatchers(HttpMethod.POST, "/produto**", "/categoria**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/produto**", "/categoria**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/produto**", "/categoria**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated())
		;
	

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationFilter authenticationJwtTokenFilter() {
		return new AuthenticationFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}