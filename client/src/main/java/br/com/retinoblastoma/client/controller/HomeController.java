package br.com.retinoblastoma.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getPaginaHome() {
		return "home";
	}
	
	@GetMapping("/nao-encontrado")
	public String getPaginaNaoEncontrada() {
		return "general/not-found";
	}
	
	
	
	
}
