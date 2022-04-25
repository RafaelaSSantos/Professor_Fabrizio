package br.com.nava.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class PrimeiroController {
	
	@GetMapping("bem-vindo")
	public String bemVindo() {return "Bem vindo";}	
	
	@PostMapping("bem-vindo")
	public String bemVindoPost() {return "Bem vindo post";}	
}