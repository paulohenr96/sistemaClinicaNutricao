package com.nutricao.aplicacaonutricao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nutricao.aplicacaonutricao.repository.UsuarioRepository;
import com.nutricao.aplicacaonutricao.service.UserServiceImplementation;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	private final UsuarioRepository repo;
//		@Bean
//	    @ConditionalOnMissingBean(UserDetailsService.class)
//	
//	    InMemoryUserDetailsManager inMemoryUserDetailsManager() { 
//	        String generatedPassword ="senha";
//	        return new InMemoryUserDetailsManager(User.withUsername("user")
//	                .password(generatedPassword).roles("USER").build());
//	    }

	@Bean
	UserServiceImplementation customUserDetailsService() {
		return new UserServiceImplementation(repo);
	}

	public WebSecurityConfig(UsuarioRepository repo) {
		super();
		this.repo = repo;
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//			.formLogin(form -> form
//				.loginPage("/login")
//				.permitAll())
//				.authorizeHttpRequests((authorize) -> authorize
//						.anyRequest().authenticated()
//			);
//		// ...
//		
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// ...
				.csrf().disable()
				.formLogin(form -> form.loginPage("/login")
						.permitAll()
						.successForwardUrl("/home").isCustomLoginPage())
				.logout().logoutSuccessUrl("/sair").and()
				
				.authorizeHttpRequests(
						(authorize) -> authorize
						.requestMatchers("/img/**").permitAll()
						.requestMatchers("/sair").permitAll()

						.anyRequest().authenticated());

;
		return http.build();
	}
	
}
