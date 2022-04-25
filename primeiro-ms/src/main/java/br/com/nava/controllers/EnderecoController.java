package br.com.nava.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.services.EnderecoService;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EnderecoDTO> getOne(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getOne(id));
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> save(@Valid @RequestBody EnderecoEntity endereco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<EnderecoDTO> update (@PathVariable Integer id, @RequestBody EnderecoEntity endereco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(id, endereco));
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable Integer id) {
		enderecoService.delete(id);
	}
}