package com.epsi.fr.arosaje;

import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class ArosajeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArosajeApplication.class, args);
	}

}
