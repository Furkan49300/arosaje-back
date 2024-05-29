package com.epsi.fr.arosaje;

import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@CrossOrigin("https://arosaje-crud-mgz9swnem-furkan49300s-projects.vercel.app")
public class ArosajeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArosajeApplication.class, args);
	}
@RestController
	@RequestMapping("/")
	static class HomeController {
		@GetMapping
	String showTimeStamp() {
			return LocalDateTime.now().toString();
		}
}
}
