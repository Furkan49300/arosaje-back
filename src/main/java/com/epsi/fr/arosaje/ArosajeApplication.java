package com.epsi.fr.arosaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@SpringBootApplication
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:8080"})
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
