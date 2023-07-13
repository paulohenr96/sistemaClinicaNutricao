package com.nutricao.aplicacaonutricao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
public class AplicacaonutricaoApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(AplicacaonutricaoApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("admin"));

	}
	
	
}
